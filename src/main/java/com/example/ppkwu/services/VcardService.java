package com.example.ppkwu.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VcardService {

    @Autowired
    private Base64Service base64Service;

    public String fromURL(String vcardUrl){
        String vcardContent = base64Service.base64Decode(vcardUrl);
        String[] vcardParts = vcardContent.split("\\n");

        String vcardName = vcardParts[0];
        String vcardOrganization = vcardParts[1];

        String[] nameParts = vcardName.split("\\s");
        String vcardStructuredName = nameParts[1] + ";" + nameParts[0];

        String vcardFile =
                "BEGIN:VCARD\n" +
                        "VERSION:2.1\n" +
                        "N:" + vcardStructuredName + "\n" +
                        "FN:" + vcardName + "\n" +
                        "ORG:" + vcardOrganization + "\n" +
                        "END:VCARD";

        return vcardFile;
    }
}
