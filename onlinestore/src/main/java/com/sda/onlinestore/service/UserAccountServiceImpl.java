package com.sda.onlinestore.service;

import com.sda.onlinestore.dto.UserAccountDto;
import com.sda.onlinestore.dto.UserAddressDto;
import com.sda.onlinestore.model.UserAccountModel;
import com.sda.onlinestore.model.UserAdressModel;
import com.sda.onlinestore.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserAccountServiceImpl implements UserAccountService {

    @Autowired
    private UserAccountRepository userAccountRepository;


    @Override
    public UserAccountDto getAccount(Long id) {
        Optional<UserAccountModel> userAccountModelOptional = userAccountRepository.findById(id);

        if(userAccountModelOptional.isPresent()) {
            UserAccountModel userAccountModel = userAccountModelOptional.get();

            UserAccountDto userAccountDto = new UserAccountDto();
            userAccountDto.setId(userAccountModel.getId());
            userAccountDto.setLogin(userAccountModel.getLogin());
            userAccountDto.setPassword(userAccountModel.getPassword());
            userAccountDto.setCity(userAccountModel.getCity());

            UserAdressModel userAdressModel = new UserAdressModel();
            UserAddressDto userAddress = userAccountDto.getUserAdress();
            userAdressModel.setId(userAddress.getId());
            userAdressModel.setCountry(userAddress.getCountry());
            userAdressModel.setCity(userAddress.getCity());
            userAdressModel.setStreet(userAddress.getStreet());
            userAdressModel.setZipcode(userAddress.getZipcode());

            userAccountModel.setUserAdress(userAdressModel);

            UserAddressDto dtoDeliveryAddress = userAccountDto.getDeliveryAdress();
            UserAdressModel deliveryAddressModel = new UserAdressModel();
            deliveryAddressModel.setId(dtoDeliveryAddress.getId());
            deliveryAddressModel.setCountry(dtoDeliveryAddress.getCountry());
            deliveryAddressModel.setCity(dtoDeliveryAddress.getCity());
            deliveryAddressModel.setStreet(dtoDeliveryAddress.getStreet());
            deliveryAddressModel.setZipcode(dtoDeliveryAddress.getZipcode());
            userAccountDto.setUserAdress(dtoDeliveryAddress);

            userAccountDto.setLogotype(userAccountModel.getLogotype());
            userAccountDto.setRoletype(userAccountModel.getRoletype());

            return userAccountDto;
        }

        return null;
    }

    @Override
    public List<UserAccountDto> getAllUsers() {
        List<UserAccountModel> userAccountModels = userAccountRepository.findAll();
        List<UserAccountDto> userAccountDtoList = new ArrayList<>();

        for(UserAccountModel userAccountModel: userAccountModels) {
            UserAccountDto userAccountDto = new UserAccountDto();
            userAccountDto.setId(userAccountModel.getId());
            userAccountDto.setLogin(userAccountModel.getLogin());
            userAccountDto.setPassword(userAccountModel.getPassword());
            userAccountDto.setCity(userAccountModel.getCity());

            UserAdressModel userAdressModel = new UserAdressModel();
            UserAddressDto userAddress = userAccountDto.getUserAdress();
            userAdressModel.setId(userAddress.getId());
            userAdressModel.setCountry(userAddress.getCountry());
            userAdressModel.setCity(userAddress.getCity());
            userAdressModel.setStreet(userAddress.getStreet());
            userAdressModel.setZipcode(userAddress.getZipcode());

            userAccountModel.setUserAdress(userAdressModel);

            UserAddressDto dtoDeliveryAddress = userAccountDto.getDeliveryAdress();
            UserAdressModel deliveryAddressModel = new UserAdressModel();
            deliveryAddressModel.setId(dtoDeliveryAddress.getId());
            deliveryAddressModel.setCountry(dtoDeliveryAddress.getCountry());
            deliveryAddressModel.setCity(dtoDeliveryAddress.getCity());
            deliveryAddressModel.setStreet(dtoDeliveryAddress.getStreet());
            deliveryAddressModel.setZipcode(dtoDeliveryAddress.getZipcode());
            userAccountDto.setUserAdress(dtoDeliveryAddress);

            userAccountDto.setRoletype(userAccountModel.getRoletype());
            userAccountDto.setLogotype(userAccountDto.getLogotype());

            userAccountDtoList.add(userAccountDto);
        }
        return userAccountDtoList;
    }

    @Override
    public void addUser(UserAccountDto userAccountDto) {
        UserAccountModel userAccountModel = new UserAccountModel();
        userAccountModel.setLogin(userAccountDto.getLogin());
        userAccountModel.setPassword(userAccountDto.getPassword());
        userAccountModel.setCity(userAccountDto.getCity());

        UserAdressModel userAdressModel = new UserAdressModel();
        UserAddressDto userAddress = userAccountDto.getUserAdress();
        userAdressModel.setId(userAddress.getId());
        userAdressModel.setCountry(userAddress.getCountry());
        userAdressModel.setCity(userAddress.getCity());
        userAdressModel.setStreet(userAddress.getStreet());
        userAdressModel.setZipcode(userAddress.getZipcode());

        userAccountModel.setUserAdress(userAdressModel);

        UserAddressDto dtoDeliveryAddress = userAccountDto.getDeliveryAdress();
        UserAdressModel deliveryAddressModel = new UserAdressModel();
        deliveryAddressModel.setId(dtoDeliveryAddress.getId());
        deliveryAddressModel.setCountry(dtoDeliveryAddress.getCountry());
        deliveryAddressModel.setCity(dtoDeliveryAddress.getCity());
        deliveryAddressModel.setStreet(dtoDeliveryAddress.getStreet());
        deliveryAddressModel.setZipcode(dtoDeliveryAddress.getZipcode());
        userAccountDto.setUserAdress(dtoDeliveryAddress);

        userAccountModel.setRoletype(userAccountDto.getRoletype());
        userAccountModel.setLogotype(userAccountDto.getLogotype());

        userAccountRepository.save(userAccountModel);
    }

    @Override
    public void updateUser(UserAccountDto userAccountDto) {
        Optional<UserAccountModel> foundUser = userAccountRepository.findById(userAccountDto.getId());
        if(foundUser.isPresent()) {
            UserAccountModel userAccountModel = foundUser.get();
            userAccountModel.setLogin(userAccountDto.getLogin());
            userAccountModel.setPassword(userAccountDto.getPassword());
            userAccountModel.setCity(userAccountDto.getCity());

            UserAdressModel userAdressModel = new UserAdressModel();
            UserAddressDto userAddress = userAccountDto.getUserAdress();
            userAdressModel.setId(userAddress.getId());
            userAdressModel.setCountry(userAddress.getCountry());
            userAdressModel.setCity(userAddress.getCity());
            userAdressModel.setStreet(userAddress.getStreet());
            userAdressModel.setZipcode(userAddress.getZipcode());

            userAccountModel.setUserAdress(userAdressModel);

            UserAddressDto dtoDeliveryAddress = userAccountDto.getDeliveryAdress();
            UserAdressModel deliveryAddressModel = new UserAdressModel();
            deliveryAddressModel.setId(dtoDeliveryAddress.getId());
            deliveryAddressModel.setCountry(dtoDeliveryAddress.getCountry());
            deliveryAddressModel.setCity(dtoDeliveryAddress.getCity());
            deliveryAddressModel.setStreet(dtoDeliveryAddress.getStreet());
            deliveryAddressModel.setZipcode(dtoDeliveryAddress.getZipcode());
            userAccountDto.setUserAdress(dtoDeliveryAddress);

            userAccountModel.setRoletype(userAccountDto.getRoletype());
            userAccountModel.setLogotype(userAccountDto.getLogotype());
        }
    }

    @Override
    public void deleteUser(Long id) {
        Optional<UserAccountModel> userAccountModelOptional = userAccountRepository.findById(id);

        if(userAccountModelOptional.isPresent()) {
            UserAccountModel userAccountModel = userAccountModelOptional.get();
            userAccountRepository.delete(userAccountModel);
        }else {
            System.out.println("User not found");
        }
    }
}
