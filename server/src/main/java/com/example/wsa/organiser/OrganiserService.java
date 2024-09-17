package com.example.wsa.organiser;

import com.example.wsa.organiser.exception.OrganiserServiceException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Organiser Service Interface
 */
public interface OrganiserService {
    List<OrganiserDTO> getOrganiserList();

    OrganiserDTO getOrganiserDetails(Integer id) throws OrganiserServiceException;

    void saveImage(String imageData, int id) throws OrganiserServiceException;

    byte[] getProfilePicture(int id);

    OrganiserDTO updateOrganiserInfo(int id, OrganiserDTO organiserDTO) throws OrganiserServiceException;

    Page<OrganiserDTO> getPageOrganiser(Pageable pageable);

    Integer saveAuthOrganiser(String email_id, String telephone_no, String post_code, String address_link, String website_link, String founding_date, String associated_clubs, String number_of_members);

    Integer getOrgVolId(Integer id, String tableName);
    
    OrganiserDTO getOrganiserHeader(int id) throws OrganiserServiceException;
}

