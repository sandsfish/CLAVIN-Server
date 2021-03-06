package edu.mit.civic.mediacloud.extractor;

import java.util.ArrayList;
import java.util.List;

import com.bericotech.clavin.extractor.LocationOccurrence;
import com.bericotech.clavin.gazetteer.CountryCode;
import com.bericotech.clavin.resolver.ResolvedLocation;

/**
 * Simple wrapper around results generated by Stanford's NER
 * @author rahulb
 */
public class ExtractedEntities {

    private List<PersonOccurrence> people;
    private List<LocationOccurrence> locations;
    private List<ResolvedLocation> resolvedLocations;
    
    public ExtractedEntities(){
        people = new ArrayList<PersonOccurrence>();
        locations = new ArrayList<LocationOccurrence>();
    }

    public void addPerson(PersonOccurrence person) {
        people.add(person);
    }

    public void addLocation(LocationOccurrence location) {
        locations.add(location);        
    }

    public List<PersonOccurrence> getPeople() {
        return people;
    }

    public List<LocationOccurrence> getLocations() {
        return locations;
    }

    public void setResolvedLocations(List<ResolvedLocation> locs) {
        resolvedLocations = locs;
    }

    public List<ResolvedLocation> getResolvedLocations() {
        return resolvedLocations;
    }

    public List<CountryCode> getUniqueCountries(){
        return getUniqueCountries(resolvedLocations);
    }
    
    public static List<CountryCode> getUniqueCountries(List<ResolvedLocation> resolvedLocations){
        List<CountryCode> countries = new ArrayList<CountryCode>();
        for(ResolvedLocation resolvedLocation: resolvedLocations){
            CountryCode country = resolvedLocation.geoname.primaryCountryCode;
            if(country==CountryCode.NULL){  // skip things that aren't in countries (ie. "Asia")
                continue;
            }
            if( !countries.contains(country) ){
                countries.add(country);
            }
        }
        return countries;
    }

}
