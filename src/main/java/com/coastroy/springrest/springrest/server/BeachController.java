package com.coastroy.springrest.springrest.server;

import com.coastroy.springrest.springrest.models.Beach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class BeachController {

  @Autowired
  private BeachService beachService;

  @RequestMapping(value = "/beaches", method = RequestMethod.GET)
  public ResponseEntity getBeaches(@RequestHeader("actionId") String actionId) {
    return ResponseEntity.status(HttpStatus.OK).body(beachService.getBeaches(actionId));
  }

  @RequestMapping(value = "/beaches", method = RequestMethod.POST)
  public ResponseEntity createBeaches(@RequestHeader("actionId") String actionId,
                                      @RequestBody Beach beach) {
    if (beachService.getBeach(beach.getId()).isPresent()) {
      return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
    } else {
      return ResponseEntity.status(HttpStatus.CREATED).body(beachService.addBeach(beach, actionId));
    }
  }

  @RequestMapping(value = "/beaches/{id}", method = RequestMethod.PUT)
  public ResponseEntity updateBeaches(@RequestHeader("actionId") String actionId,
                                      @RequestBody Beach beach) {
    Optional<Beach> updatedBeach = beachService.updateOrAddBeach(beach, actionId);
    if (updatedBeach.isPresent()) {
      return ResponseEntity.status(HttpStatus.OK).body(updatedBeach.get());
    } else {
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

  }

  @RequestMapping(value = "/beaches/{id}", method = RequestMethod.PATCH)
  public ResponseEntity updateBeachesPartially(@RequestHeader("actionId") String actionId,
                                               @RequestBody Beach beach) {
    Optional<Beach> updatedBeach = beachService.updateBeach(beach, actionId);
    if (updatedBeach.isPresent()) {
      return ResponseEntity.status(HttpStatus.OK).body(updatedBeach.get());
    } else {
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
  }

  @RequestMapping(value = "/beaches/{id}", method = RequestMethod.DELETE)
  public ResponseEntity deleteBeach(@RequestHeader("actionId") String actionId,
                                    @PathVariable int id) {
    Optional<Beach> beachOptional = beachService.getBeach(id);
    if (beachOptional.isPresent()) {
      if (beachService.deleteBeach(beachOptional, actionId)) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
      } else {
        return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
      }
    } else {
      return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
    }
  }
}
