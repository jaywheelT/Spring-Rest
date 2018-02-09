package com.coastroy.springrest.springrest.server.service;

import com.coastroy.springrest.springrest.models.Beach;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BeachService {
  private Map<String, String> actionMap;
  private List<Beach> beaches;

  public BeachService() {
    actionMap = new HashMap<>();
    beaches = new ArrayList<>();
    beaches.add(new Beach(1, "Cliff Beach", 200));
    beaches.add(new Beach(2, "Barcelona Beach", 300));
    beaches.add(new Beach(3, "Nice Beach", 400));
  }

  public Map<String, String> getActionMap() {
    return actionMap;
  }

  public List<Beach> getBeaches(String actionId) {
    actionMap.put(actionId, "getBeaches");
    return beaches;
  }

  public Optional<Beach> getBeach(int beachId) {
    return beaches.stream()
        .filter(beach -> beach.getId() == beachId)
        .findFirst();
  }

  public Beach addBeach(Beach beach, String actionId) {
    actionMap.put(actionId, "addBeach");
    beaches.add(beach);
    return beaches.get(beaches.size() - 1);
  }

  public Optional<Beach> updateOrAddBeach(Beach updatedBeach, String actionId) {
    actionMap.put(actionId, "updateOrAddBeach");
    Optional<Beach> originBeach = getBeach(updatedBeach.getId());
    if (originBeach.isPresent()) {
      Beach beach = originBeach.get();
      beaches.set(beaches.indexOf(beach), updatedBeach);
    } else {
      beaches.add(updatedBeach);
    }
    return getBeach(updatedBeach.getId());
  }

  public Optional<Beach> updateBeach(Beach updatedBeach, String actionId) {
    actionMap.put(actionId, "updateBeach");
    Optional<Beach> originBeach = getBeach(updatedBeach.getId());
    if (originBeach.isPresent()) {
      Beach beach = originBeach.get();
      if (updatedBeach.getCapacity() != 0) {
        beach.setCapacity(updatedBeach.getCapacity());
      }
      if (updatedBeach.getName() != null) {
        beach.setName(updatedBeach.getName());
      }
      beaches.set(beaches.indexOf(beach), beach);
      return getBeach(updatedBeach.getId());
    } else {
      return originBeach;
    }
  }

  public boolean deleteBeach(Optional<Beach> originBeach, String actionId) {
    actionMap.put(actionId, "deleteBeach");
    if (originBeach.isPresent()) {
      Beach beach = originBeach.get();
      beaches.remove(beach);
    }
    return true;
  }
}
