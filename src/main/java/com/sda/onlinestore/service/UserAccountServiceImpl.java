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

            UserAdressModel userAdressModel = userAccountModel.getDeliveryAdress();
            UserAddressDto userAddressDeliveryDto = new UserAddressDto();
            userAddressDeliveryDto.setId(userAccountModel.getId());
            userAddressDeliveryDto.setCountry(userAdressModel.getCountry());
            userAddressDeliveryDto.setCity(userAdressModel.getCity());
            userAddressDeliveryDto.setStreet(userAdressModel.getStreet());
            userAddressDeliveryDto.setZipcode(userAdressModel.getZipcode());
            userAccountDto.setDeliveryAdress(userAddressDeliveryDto);

            UserAdressModel userAddress = userAccountModel.getUserAdress();
            UserAddressDto userAddressDto = new UserAddressDto();
            userAddressDto.setId(userAddress.getId());
            userAddressDto.setCountry(userAddress.getCountry());
            userAddressDto.setCity(userAddress.getCity());
            userAddressDto.setStreet(userAddress.getStreet());
            userAddressDto.setZipcode(userAddress.getZipcode());
            userAccountDto.setUserAdress(userAddressDto);

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

            UserAdressModel userAdressModel = userAccountModel.getDeliveryAdress();
            UserAddressDto userAddressDeliveryDto = new UserAddressDto();
            userAddressDeliveryDto.setId(userAccountModel.getId());
            userAddressDeliveryDto.setCountry(userAdressModel.getCountry());
            userAddressDeliveryDto.setCity(userAdressModel.getCity());
            userAddressDeliveryDto.setStreet(userAdressModel.getStreet());
            userAddressDeliveryDto.setZipcode(userAdressModel.getZipcode());
            userAccountDto.setDeliveryAdress(userAddressDeliveryDto);

            UserAdressModel userAddress = userAccountModel.getUserAdress();
            UserAddressDto userAddressDto = new UserAddressDto();
            userAddressDto.setId(userAddress.getId());
            userAddressDto.setCountry(userAddress.getCountry());
            userAddressDto.setCity(userAddress.getCity());
            userAddressDto.setStreet(userAddress.getStreet());
            userAddressDto.setZipcode(userAddress.getZipcode());
            userAccountDto.setUserAdress(userAddressDto);

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

        UserAdressModel userAdressModel = userAccountModel.getDeliveryAdress();
        UserAddressDto userAddressDeliveryDto = new UserAddressDto();
        userAddressDeliveryDto.setId(userAccountModel.getId());
        userAddressDeliveryDto.setCountry(userAdressModel.getCountry());
        userAddressDeliveryDto.setCity(userAdressModel.getCity());
        userAddressDeliveryDto.setStreet(userAdressModel.getStreet());
        userAddressDeliveryDto.setZipcode(userAdressModel.getZipcode());
        userAccountDto.setDeliveryAdress(userAddressDeliveryDto);

        UserAdressModel userAddress = userAccountModel.getUserAdress();
        UserAddressDto userAddressDto = new UserAddressDto();
        userAddressDto.setId(userAddress.getId());
        userAddressDto.setCountry(userAddress.getCountry());
        userAddressDto.setCity(userAddress.getCity());
        userAddressDto.setStreet(userAddress.getStreet());
        userAddressDto.setZipcode(userAddress.getZipcode());
        userAccountDto.setUserAdress(userAddressDto);

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

            UserAdressModel userAdressModel = userAccountModel.getDeliveryAdress();
            UserAddressDto userAddressDeliveryDto = new UserAddressDto();
            userAddressDeliveryDto.setId(userAccountModel.getId());
            userAddressDeliveryDto.setCountry(userAdressModel.getCountry());
            userAddressDeliveryDto.setCity(userAdressModel.getCity());
            userAddressDeliveryDto.setStreet(userAdressModel.getStreet());
            userAddressDeliveryDto.setZipcode(userAdressModel.getZipcode());
            userAccountDto.setDeliveryAdress(userAddressDeliveryDto);

            UserAdressModel userAddress = userAccountModel.getUserAdress();
            UserAddressDto userAddressDto = new UserAddressDto();
            userAddressDto.setId(userAddress.getId());
            userAddressDto.setCountry(userAddress.getCountry());
            userAddressDto.setCity(userAddress.getCity());
            userAddressDto.setStreet(userAddress.getStreet());
            userAddressDto.setZipcode(userAddress.getZipcode());
            userAccountDto.setUserAdress(userAddressDto);

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
