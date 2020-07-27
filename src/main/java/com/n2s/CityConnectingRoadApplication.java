package com.n2s;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.n2s.model.City;

@RestController
@EnableAutoConfiguration
@SpringBootApplication
public class CityConnectingRoadApplication {

	static Scanner scanner;
	static ArrayList<City> citylist = new ArrayList<City>();
	static int check = 0;

	public static void main(String[] args) {
		SpringApplication.run(CityConnectingRoadApplication.class, args);
	}

	@RequestMapping("/connected/origin={city1}&destination={city2}")
	public String connectRoad(@PathVariable("city1") String orgCity, @PathVariable("city2") String dstCity) {

		try {
			scanner = new Scanner(new File("city.txt"));
			citylist.clear();
			check = 0;

			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				// System.out.println(line);
				String temp[] = line.split(", ");
				citylist.add(new City(temp[0], temp[1]));
			}

			if (directRouteCheck(orgCity, dstCity) == 1) {
				return "Yes";
			} else if (commonCheck(orgCity, dstCity) == 1) {
				return "Yes";
			} else if (orgCity.equalsIgnoreCase(dstCity)) {
				return "Invalid selection : Both Origin and Destination city are same";
			} else

				return "No";

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return "No";
	}

	public int directRouteCheck(String orgCity, String dstCity) {
		for (int i = 0; i < citylist.size(); i++) {
			// System.out.println(al.get(i).getOriginCity()+"-"+al.get(i).getDestnCity());
			if ((citylist.get(i).getOriginCity().equalsIgnoreCase(orgCity)
					&& citylist.get(i).getDestnCity().equalsIgnoreCase(dstCity))
					|| (citylist.get(i).getOriginCity().equalsIgnoreCase(dstCity)
							&& citylist.get(i).getDestnCity().equalsIgnoreCase(orgCity))) {
				check = 1;
				break;
			}
		}

		return check;
	}

	public static int commonCheck(String orgCity, String dstCity) {
		Set<String> s1 = new HashSet<String>();
		Set<String> s2 = new HashSet<String>();

		////// Getting roads assigned for first city ////////////////
		for (int i = 0; i < citylist.size(); i++) {
			if (citylist.get(i).getOriginCity().equalsIgnoreCase(orgCity))
				s1.add(citylist.get(i).getDestnCity());
			else if (citylist.get(i).getDestnCity().equalsIgnoreCase(orgCity))
				s1.add(citylist.get(i).getOriginCity());
		}
		/////////////////////////////////////////////////////////////

		////// Getting roads assigned for first city ////////////////
		for (int i = 0; i < citylist.size(); i++) {
			if (citylist.get(i).getOriginCity().equalsIgnoreCase(dstCity))
				s2.add(citylist.get(i).getDestnCity());
			else if (citylist.get(i).getDestnCity().equalsIgnoreCase(dstCity))
				s2.add(citylist.get(i).getOriginCity());
		}
		/////////////////////////////////////////////////////////////

		for (String s : s1)
//			System.out.print(s+"-");
//		System.out.println("////////////////////////////////////////");
			for (String j : s2)
//			System.out.print(j+"-");
//		System.out.println("////////////////////////////////////////");

				s1.retainAll(s2);

//		System.out.println("s1 size is "+s1.size()); 

		return s1.size();
	}

}
