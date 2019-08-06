package jp.ivs.controller;

import jp.ivs.form.PersonForm;
import jp.ivs.mapper.PersonMapper;
import jp.ivs.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController
{
    private static List<Person> persons = new ArrayList<Person>();

    static {
        persons.add(new Person("Bill", "Gates"));
        persons.add(new Person("Steve", "Jobs"));
    }
    @Autowired
    PersonMapper mapper;

    // Được tiêm vào (inject) từ application.properties.
    @Value("${welcome.message}")
    private String message;

    @Value("${error.message}")
    private String errorMessage;

    @RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
    public String index(Model model) {

        model.addAttribute("message", message);

        return "index";
    }

    @RequestMapping(value = { "/personList" }, method = RequestMethod.GET)
    public String personList(Model model)
    {
        List<Person> list = mapper.getAllPerson();
        model.addAttribute("persons", list);

        return "personList";
    }

    @RequestMapping(value = { "/addPerson" }, method = RequestMethod.GET)
    public String showAddPersonPage(Model model) {
        //List<Person> list = mapper.getAllPerson();
        PersonForm personForm = new PersonForm();
        model.addAttribute("personForm", personForm);

        return "addPerson";
    }

    @RequestMapping(value = { "/addPerson" }, method = RequestMethod.POST)
    public String savePerson(Model model, //
                             @ModelAttribute("personForm") PersonForm personForm) {

        String firstName = personForm.getFirstName();
        String lastName = personForm.getLastName();

        if (firstName != null && firstName.length() > 0 //
                && lastName != null && lastName.length() > 0) {
            Person newPerson = new Person(firstName, lastName);
            persons.add(newPerson);

            return "redirect:/personList";
        }

        model.addAttribute("errorMessage", errorMessage);
        return "addPerson";
    }
}
