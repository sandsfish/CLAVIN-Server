package edu.mit.civic.mediacloud.where.disambiguation;

import java.util.ArrayList;
import java.util.List;

import com.bericotech.clavin.gazetteer.FeatureClass;
import com.bericotech.clavin.resolver.ResolvedLocation;

public class TopAdminPopulatedPass extends GenericPass {

    @Override
    protected List<List<ResolvedLocation>> disambiguate(
            List<List<ResolvedLocation>> possibilitiesToDo,
            List<ResolvedLocation> bestCandidates) {
        List<List<ResolvedLocation>> possibilitiesToRemove = new ArrayList<List<ResolvedLocation>>();
        
        for( List<ResolvedLocation> candidates: possibilitiesToDo){
            boolean foundOne = false;
            for( ResolvedLocation candidate: candidates) {
                if(!foundOne && (candidate.geoname.population>0) && 
                        (candidate.geoname.featureClass==FeatureClass.A || candidate.geoname.featureClass==FeatureClass.P)){
                    bestCandidates.add(candidate);
                    possibilitiesToRemove.add(candidates);
                    foundOne = true;
                }
            }
        }

        return possibilitiesToRemove;
    }

    @Override
    public String getDescription() {
        return "Pick the top Admin Region or Populated Place remaining";
    }
    
}
