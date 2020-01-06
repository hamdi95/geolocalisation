package tn.esprit.config;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import tn.esprit.entity.Link;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class BandAlreadyLinkedException extends Exception{

    public BandAlreadyLinkedException(Link link) {
        super(String.format("Band with id:%s is linked with user with id:%s",link.getBand().getId(),link.getUser().getId().toString()));
    }
}
