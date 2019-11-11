package ru.job4j_spring.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.job4j_spring.dao.CarsStore;
import ru.job4j_spring.dao.DAO;
import ru.job4j_spring.filters.FilterList;
import ru.job4j_spring.models.AdUser;
import ru.job4j_spring.models.Cars;
import ru.job4j_spring.models.Image;
import ru.job4j_spring.services.CarsService;

@Controller
public class HomeController {
	private final static String SAVE_DIR = "uploadFiles";
	@Autowired
	@Qualifier("StoreJPA")
	private DAO carsStore;
	@Autowired
	CarsService carsService;

    /**
     * Change status to SOLD
     */
	@RequestMapping(value="/adsupdate",  method = RequestMethod.POST)
	public ModelAndView adsUpdate(@RequestParam(value = "id", required = true) String id, HttpSession session, Model model) throws IOException{
		Cars car = carsStore.findCarById(id);
		if (car != null && car.getAduser().getName().equals(session.getAttribute("user"))) {
			car.setStatus("sold");
			carsStore.updateCar(car);
		}
		return new ModelAndView("redirect:/carslist");
	}

    /**
     * Image download
     */
	@RequestMapping(value="/image",  method = RequestMethod.GET)
	public @ResponseBody byte[] downloadImage(@RequestParam(value = "fileName", required = true) String fileName, HttpServletRequest request) throws IOException{
		ServletContext servletContext = request.getServletContext();
		String appPath = servletContext.getRealPath("");
		String pullPath = appPath + File.separator + SAVE_DIR;
		File file = new File(pullPath, fileName);
		InputStream in = new FileInputStream(file);
		return IOUtils.toByteArray(in);
	}

    /**
     * Show full car information
     */
	@RequestMapping(value="/showads", method = RequestMethod.GET)
	public ModelAndView showAds(@RequestParam(value = "id", required = true) String car_id, Model model) throws IOException{
		Cars car = carsStore.findCarById(car_id);
		model.addAttribute("car", car);
		model.addAttribute("imageset", car.getImageSet());
		return new ModelAndView("showads");
	}

    /**
     * Show form - Add new car
     */
	@RequestMapping(value="/addads",  method = RequestMethod.POST)
	public ModelAndView addAds(HttpServletRequest req, Model model) throws IOException{
		Cars car = getCars(req);
		carsStore.addCar(car);
		return new ModelAndView("redirect:/carslist");
	}

    /**
     * Add new car to DB
     */
	@RequestMapping(value="/addads",  method = RequestMethod.GET)
	public ModelAndView showAdsForm(Model model) throws IOException{
		model.addAttribute("body", carsStore.findAllBody());
		model.addAttribute("brand", carsStore.findAllBrand());
		model.addAttribute("drive", carsStore.findAllDrive());
		model.addAttribute("engine", carsStore.findAllEngine());
		model.addAttribute("transmission", carsStore.findAllTransmission());
		model.addAttribute("wheel", carsStore.findAllWheel());
		return new ModelAndView("addads");
	}

    /**
     * User registration
     */
	@RequestMapping(value="/reg",  method = RequestMethod.GET)
	public ModelAndView showRegForm() throws IOException{
		return new ModelAndView("adduser");
	}

	@RequestMapping(value="/reg",  method = RequestMethod.POST)
	public ModelAndView regUser(@ModelAttribute("user") AdUser adUser, HttpSession session, Model model) throws IOException{
		if (carsStore.addAdUser(adUser)) {
			session.setAttribute("user", carsStore.findAdUserByNamePass(adUser.getName(), adUser.getPassword()).getName());
		}
		return new ModelAndView("redirect:/carslist");
	}

    /**
     * User authorization
     */
	@RequestMapping(value="/signin")
	public ModelAndView signInShowForm() throws IOException{
		return new ModelAndView("signin");
	}

	@RequestMapping(value="/signin", method = RequestMethod.POST)
	public ModelAndView signIn(@RequestParam(value = "name", required = true) String name, @RequestParam(value = "password", required = true) String password, Model model, HttpSession session) throws IOException{
		AdUser adUser = carsStore.findAdUserByNamePass(name, password);
		if (adUser != null) {
			session.setAttribute("user", adUser.getName());
			return new ModelAndView("redirect:/carslist");
		} else {
			model.addAttribute("error", "Credential invalid.");
			return new ModelAndView("signin");
		}
	}

    /**
     * User sign-out
     */
	@RequestMapping(value="/signout")
	public ModelAndView signOut(HttpSession session, Model model) throws IOException{
		session.invalidate();
		return new ModelAndView("redirect:/carslist");
	}

	@RequestMapping(value="/")
	public ModelAndView test() throws IOException{
		return new ModelAndView("redirect:/carslist");
	}

    /**
     * Home page - cars list
     */
	@RequestMapping(value="/carslist", method = RequestMethod.GET)
	public ModelAndView getCarsList(Model model) throws IOException{
		model.addAttribute("brand", carsStore.findAllBrand());
		return new ModelAndView("index");
	}

	@RequestMapping(value="/carslist", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody List<Cars> getJsonCarsList(@RequestBody FilterList filterList) throws IOException{
		List<Cars> cars_list;
		cars_list = carsStore.filtersCars(filterList);
		if (filterList.getFoto().equals("true")) {
			cars_list = cars_list.stream()
					.filter(car -> !car.getImageSet().isEmpty())
					.collect(Collectors.toList());
		}
		return cars_list;
	}

	private Cars getCars(HttpServletRequest req) {
		Cars car = new Cars();
		String appPath = req.getServletContext().getRealPath("");
		String savePath = appPath + File.separator + SAVE_DIR;
		Set<Image> imageSet = new HashSet<>();
		if(ServletFileUpload.isMultipartContent(req)) {
			try {
				List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(req);
				for(FileItem item : multiparts) {
					if(!item.isFormField() && !item.getName().isEmpty()) {
						String name = System.currentTimeMillis() + new File(item.getName()).getName();
						item.write( new File(savePath + File.separator + name));
						imageSet.add(new Image(name));
					}else {
						switch (item.getFieldName()) {
							case "cost": car.setCost(item.getString()); break;
							case "mileage": car.setMileage(item.getString()); break;
							case "power": car.setPower(item.getString()); break;
							case "title": car.setTitle(item.getString()); break;
							case "addesc": car.setAddesc(item.getString()); break;
							case "body": car.setBody(carsStore.findBodyById(item.getString())); break;
							case "brand": car.setBrand(carsStore.findBrandById(item.getString())); break;
							case "drive": car.setDrive(carsStore.findDriveById(item.getString())); break;
							case "engine": car.setEngine(carsStore.findEngineById(item.getString())); break;
							case "transmission": car.setTransmission(carsStore.findTransmissionById(item.getString())); break;
							case "wheel": car.setWheel(carsStore.findWheelById(item.getString())); break;
						}
					}
				}
			} catch (Exception ex) {
				req.setAttribute("error", "File Upload Failed due to " + ex);
			}
		}else{
			req.setAttribute("error", "Sorry this Servlet only handles file upload request");
		}
		String username = (String) req.getSession().getAttribute("user");
		car.setAduser(carsStore.findAdUserByName(username));
		if (!imageSet.isEmpty()) {
			car.setImageSet(imageSet);
		}
		return car;
	}
}
