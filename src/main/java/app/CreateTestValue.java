package app;

import app.Model.Role;
import app.Model.User;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

public class CreateTestValue {

    public static final String REST_SERVICE_URI = "http://localhost:8080/";

    //public static final String REST_SERVICE_URI = "<a class="vglnk" href="http://localhost:8080/SpringBootRestApi/api" rel="nofollow"><span>http</span><span>://</span><span>localhost</span><span>:</span><span>8080</span><span>/</span><span>SpringBootRestApi</span><span>/</span><span>api</span></a>";


    /* GET */
    /*
    @SuppressWarnings("unchecked")
    private static void listAllUsers(){
        System.out.println("Testing listAllUsers API-----------");

        RestTemplate restTemplate = new RestTemplate();
        List<LinkedHashMap<String, Object>> usersMap = restTemplate.getForObject(REST_SERVICE_URI+"/user/", List.class);

        if(usersMap!=null){
            for(LinkedHashMap<String, Object> map : usersMap){
                System.out.println("User : id="+map.get("id")+", Name="+map.get("name")+", Age="+map.get("age")+", Salary="+map.get("salary"));;
            }
        }else{
            System.out.println("No user exist----------");
        }
    }
    */

    /* GET */
    /*
    private static void getUser(){
        System.out.println("Testing getUser API----------");
        RestTemplate restTemplate = new RestTemplate();
        User user = restTemplate.getForObject(REST_SERVICE_URI+"/user/1", User.class);
        System.out.println(user);
    }
    */

    /* POST */
    private static void createRoles() {
        System.out.println("Testing create Role API----------");
        RestTemplate restTemplate = new RestTemplate();
        Role role = new Role("Rule Developer", "Rollenbeschreibung Developer");
        URI uri = restTemplate.postForLocation(REST_SERVICE_URI+"/roles/", role, Role.class);
        System.out.println("Location : "+uri.toASCIIString());
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

    public static void main(String args[]){
        createRoles();
        /*
        listAllUsers();
        getUser();
        createUser();
        listAllUsers();
        updateUser();
        listAllUsers();
        deleteUser();
        listAllUsers();
        deleteAllUsers();
        listAllUsers();
         */
    }
}
