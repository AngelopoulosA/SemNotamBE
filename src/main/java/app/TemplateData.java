package app;

import app.Model.Role;
import app.Model.User;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.List;


public class TemplateData {

    public static final String REST_SERVICE_URI = "http://localhost:8080";
    public static Boolean isCreated = false;

    /* GET */
    @SuppressWarnings("unchecked")
    private void listAllUsers(){
        System.out.println("\nTesting listAllUsers API-----------\n");

        RestTemplate restTemplate = new RestTemplate();
        List<LinkedHashMap<String, Object>> usersMap = restTemplate.getForObject(REST_SERVICE_URI+"/users/", List.class);

        if(usersMap!=null){
            for(LinkedHashMap<String, Object> map : usersMap){
                System.out.println("User : id="+map.get("id")+", Name="+map.get("name")+", Username="+map.get("username")+", password="+map.get("password"));;
            }
        }else{
            System.out.println("No user exist----------");
        }
    }

    /* GET */
    @SuppressWarnings("unchecked")
    private void listAllRoles(){
        System.out.println("" +
                "\nTesting listAllRoles API-----------\n");

        RestTemplate restTemplate = new RestTemplate();
        List<LinkedHashMap<String, Object>> usersMap = restTemplate.getForObject(REST_SERVICE_URI+"/roles/", List.class);

        if(usersMap!=null){
            for(LinkedHashMap<String, Object> map : usersMap){
                System.out.println("Role : id="+map.get("id")+", Name="+map.get("name")+", description="+map.get("description"));;
            }
        }else{
            System.out.println("No role exist----------");
        }
    }

    /* GET */
    public static User getUser (long userId){
        RestTemplate restTemplate = new RestTemplate();
        User user = restTemplate.getForObject(REST_SERVICE_URI+"/users/"+userId, User.class);
        return user;
    }

    public TemplateData(){
        if (getRole(1)== null){
            this.isCreated = true;
            createRoles();
            createUsers();
        } else {
            System.out.println("\nTemplate Data are available!!!\n");
            listAllRoles();
            listAllUsers();
        }
    }

    /* POST */
    public void createRoles() {
        Role role;
        String result;

        System.out.println("Testing create Role API----------");
        RestTemplate restTemplate = new RestTemplate();
        role = new Role("Rule Developer", "Rollenbeschreibung Developer");
        result = restTemplate.postForObject(REST_SERVICE_URI+"/roles/", role, String.class) ;
        System.out.println("\n Role : "+result + "\n");

        role = new Role("Repository Administrator", "Rollenbeschreibung Repository Administrator");
        result = restTemplate.postForObject(REST_SERVICE_URI+"/roles/", role, String.class) ;
        System.out.println("\n Role : "+result + "\n");

        role = new Role("Domain Expert", "Rollenbeschreibung Domain Expert");
        result = restTemplate.postForObject(REST_SERVICE_URI+"/roles/", role, String.class) ;
        System.out.println("\n Role : "+result + "\n");

        role = new Role("User", "Rollenbeschreibung User");
        result = restTemplate.postForObject(REST_SERVICE_URI+"/roles/", role, String.class) ;
        System.out.println("\n Role : "+result + "\n");
    }

    /* GET */
    public static Role getRole(long roleId){
        RestTemplate restTemplate = new RestTemplate();
        Role role = restTemplate.getForObject(REST_SERVICE_URI+"/roles/"+roleId, Role.class);
        return role;
    }

    /* POST */
    public void createUsers() {
        User user;
        String result;

        System.out.println("Testing create Role API----------");
        RestTemplate restTemplate = new RestTemplate();

        user = new User("Daniel", "daniel", "da", getRole(1));
        result = restTemplate.postForObject(REST_SERVICE_URI+"/users/", user, String.class) ;
        System.out.println("\n User : "+result + "\n");

        user = new User("Dominik", "dominik", "do", getRole(3));
        result = restTemplate.postForObject(REST_SERVICE_URI+"/users/", user, String.class) ;
        System.out.println("\n Role : "+result + "\n");

        user = new User("Peter", "peter", "pe", getRole(2));
        result = restTemplate.postForObject(REST_SERVICE_URI+"/users/", user, String.class) ;
        System.out.println("\n Role : "+result + "\n");

        user = new User("Anna", "anna", "an", getRole(4));
        result = restTemplate.postForObject(REST_SERVICE_URI+"/users/", user, String.class) ;
        System.out.println("\n Role : "+result + "\n");
    }

/*
    private static void createUser() {
        System.out.println("Testing create User API----------");
        RestTemplate restTemplate = new RestTemplate();
        User user = new User(0,"Sarah",51,134);
        URI uri = restTemplate.postForLocation(REST_SERVICE_URI+"/user/", user, User.class);
        System.out.println("Location : "+uri.toASCIIString());
    }
    */

    /* PUT */
    /*
    private static void updateUser() {
        System.out.println("Testing update User API----------");
        RestTemplate restTemplate = new RestTemplate();
        User user  = new User(1,"Tomy",33, 70000);
        restTemplate.put(REST_SERVICE_URI+"/user/1", user);
        System.out.println(user);
    }
    */

    /* DELETE */
    /*
    private static void deleteUser() {
        System.out.println("Testing delete User API----------");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(REST_SERVICE_URI+"/user/3");
    }
    */


    /* DELETE */
    /*
    private static void deleteAllUsers() {
        System.out.println("Testing all delete Users API----------");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(REST_SERVICE_URI+"/user/");
    }
    */
}
