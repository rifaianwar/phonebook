package com.r3s.phonebook.service;

import com.r3s.phonebook.entity.ContactEntity;
import com.r3s.phonebook.model.request.ContactRequest;
import com.r3s.phonebook.model.response.GeneralResponse;
import com.r3s.phonebook.repository.ContactRepository;
import com.r3s.phonebook.utility.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public GeneralResponse createContact(ContactRequest inMsg) {
        log.info("REQUEST Add Contact [{}]", JsonUtils.convertObjectToString(inMsg));
        GeneralResponse outMsg = new GeneralResponse();
        try {
            ContactEntity contactEntity = new ContactEntity();
            contactEntity.setCreatedDate(new Date());
            contactEntity.setFirstName(inMsg.getFirstName());
            contactEntity.setLastName(inMsg.getLastName());
            contactEntity.setEmail(inMsg.getEmail());
            contactEntity.setPhoneNumber(inMsg.getPhoneNumber());
            contactEntity.setNote(inMsg.getNote());
            contactEntity.setGrup(inMsg.getGroup());
            contactRepository.save(contactEntity);
            log.info(" Contact Added: {}", contactEntity);

            outMsg.setMessage("Successfully added contact");
            outMsg.setData(contactEntity);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("CATCH Add Contact[{}]", JsonUtils.convertObjectToString(e));
            outMsg.setMessage("Error Add Contact : " + e.getMessage());
        }
        return outMsg;
    }

    public GeneralResponse getByPhoneNumber(String phoneNumber) {
        log.info("REQUEST Get Contact By Phone Number [{}]", phoneNumber);
        GeneralResponse outMsg = new GeneralResponse();
        try {
            List<ContactEntity> listContact = contactRepository.getAllByPhoneNumber(phoneNumber);
            if (listContact.isEmpty()) {
                outMsg.setMessage("Phone Number not found");
                return outMsg;
            }
            outMsg.setMessage("Success");
            outMsg.setData(listContact);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("CATCH Get Contact By Phone Number [{}]", JsonUtils.convertObjectToString(e));
            outMsg.setMessage("Error Get Contact By Phone Number : " + e.getMessage());
        }
        return outMsg;
    }

    public GeneralResponse getByGroup(String group) {
        log.info("REQUEST Get Contact By Group [{}]", group);
        GeneralResponse outMsg = new GeneralResponse();
        try {
            List<ContactEntity> listContact = contactRepository.getAllByGrup(group);
            if (listContact.isEmpty()) {
                outMsg.setMessage("Group not found");
                return outMsg;
            }
            outMsg.setMessage("Success");
            outMsg.setData(listContact);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("CATCH Get Contact By Group [{}]", JsonUtils.convertObjectToString(e));
            outMsg.setMessage("Error Get Contact ByGroup : " + e.getMessage());
        }
        return outMsg;
    }

    public GeneralResponse doUpdate(ContactRequest inMsg) {
        log.info("REQUEST Update Contact  [{}]", JsonUtils.convertObjectToString(inMsg));
        GeneralResponse outMsg = new GeneralResponse();
        try {
            if (contactRepository.findById(inMsg.getId()).isEmpty()) {
                outMsg.setMessage("Id not found");
                return outMsg;
            }

            ContactEntity contact = contactRepository.findById(inMsg.getId()).get();
            contact.setFirstName(inMsg.getFirstName() == null ? contact.getFirstName() :
                    !inMsg.getFirstName().isEmpty() ? inMsg.getFirstName() : contact.getFirstName());
            contact.setLastName(inMsg.getLastName() == null ? contact.getLastName() :
                    !inMsg.getLastName().isEmpty() ? inMsg.getLastName() : contact.getLastName());
            contact.setPhoneNumber(inMsg.getPhoneNumber() == null ? contact.getPhoneNumber() :
                    !inMsg.getPhoneNumber().isEmpty() ? inMsg.getPhoneNumber() : contact.getPhoneNumber());
            contact.setEmail(inMsg.getEmail() == null ? contact.getEmail() :
                    !inMsg.getEmail().isEmpty() ? inMsg.getEmail() : contact.getEmail());
            contact.setNote(inMsg.getNote() == null ? contact.getNote() :
                    !inMsg.getNote().isEmpty() ? inMsg.getNote() : contact.getNote());
            contact.setGrup(inMsg.getGroup() == null ? contact.getGrup() :
                    !inMsg.getGroup().isEmpty() ? inMsg.getGroup() : contact.getGrup());
            contact.setUpdatedDate(new Date());
            contactRepository.save(contact);
            log.info("Category created: {}", contact);

            outMsg.setMessage("Success");
            outMsg.setData(contact);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("CATCH Update Contact[{}]", JsonUtils.convertObjectToString(e));
            outMsg.setMessage("Error Update Contact : " + e.getMessage());
        }
        return outMsg;
    }

    public GeneralResponse deleteById(Long id) {
        log.info("REQUEST Deleted Contact By Id [{}]", id);
        GeneralResponse outMsg = new GeneralResponse();
        try {
            if (contactRepository.findById(id).isEmpty()) {
                outMsg.setMessage("Id not found");
                return outMsg;
            }

            ContactEntity contactEntity = contactRepository.getById(id);
            contactEntity.setDeletedDate(new Date()); // for soft delete
            contactRepository.save(contactEntity);
//            contactRepository.deleteById(id); // for hard delete

            outMsg.setMessage("Success");
        } catch (Exception e) {
            e.printStackTrace();
            log.info("CATCH Deleted Contact By Id [{}]", JsonUtils.convertObjectToString(e));
            outMsg.setMessage("Error Deleted Contact By Id : " + e.getMessage());
        }
        return outMsg;
    }


}
