package resources;

import pojo.AddPlace;
import pojo.Location;

import java.util.ArrayList;
import java.util.List;

public class BuildTestData {

    public AddPlace addPlacePayload(String name, String language, String address) {
        List<String> list = new ArrayList<>();
        list.add("shoe park");
        list.add("shop");

        Location location = new Location();
        location.setLat(-38.383494);
        location.setLng(33.427362);

        AddPlace addPlace = new AddPlace();
        addPlace.setAccuracy("50");
        addPlace.setAddress(address);
        addPlace.setLanguage(language);
        addPlace.setWebsite("http://google.com");
        addPlace.setName(name);
        addPlace.setPhone_number("\"(+91) 983 893 3937");
        addPlace.setLocation(location);
        addPlace.setTypes(list);
        return addPlace;
    }

    public String deletePlacePayload(String placeID) {
        return "{\r\n\"place_id\":\"" + placeID + "\"\r\n}";
    }
}
