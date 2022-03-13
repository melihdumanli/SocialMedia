package com.bilgeadam.service;

import com.bilgeadam.config.security.JwtTokenManager;
import com.bilgeadam.dto.request.DoLoginRequestDto;
import com.bilgeadam.dto.request.FindByAuthIdDto;
import com.bilgeadam.dto.request.RegisterRequestDto;
import com.bilgeadam.dto.response.DoLoginResponseDto;
import com.bilgeadam.manager.ProfileManager;
import com.bilgeadam.mapper.UserMapper;
import com.bilgeadam.repository.IUserRepository;
import com.bilgeadam.repository.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    IUserRepository iUserRepository;

    @Autowired
    UserMapper  userMapper;

    @Autowired
    ProfileManager profileManager;

    @Autowired
    JwtTokenManager jwtTokenManager;


    /**
     * Returns user id after creating new user
     * @param dto
     * @return
     */
    public User saveReturnUser(RegisterRequestDto dto){
        User user = userMapper.toUser(dto);
        iUserRepository.save(user);
        return user;
    }

    public void save(User user){
        iUserRepository.save(user);
    }

    public void update(User user){
        iUserRepository.save(user);
    }

    public void delete(User user){
        iUserRepository.delete(user);
    }

    public List<User> findAll(){
        return iUserRepository.findAll();
    }

    public DoLoginResponseDto findByUsernameAndPassword(DoLoginRequestDto dto){
        Optional<User> user = iUserRepository
                .findByUsernameAndPassword(dto.getUsername(), dto.getPassword());
        if (user.isPresent())
            return userMapper.toDoLoginResponseDto(user.get());
        return new DoLoginResponseDto();
    }

    public boolean isUser(String username,String password){
        Optional<User> user = iUserRepository.findByUsernameAndPassword(username, password);
        if (user.isPresent())
            return true;
        return false;
    }

    public DoLoginResponseDto getProfile(DoLoginRequestDto dto){
        /**
         * Kullancı adı ve şifre den auth Db de ki kullanıcıyı arar.
         */
        Optional<User> user =iUserRepository.findByUsernameAndPassword(dto.getUsername(), dto.getPassword());
        if (user.isPresent()){
            /**
             * Eğer kullanıvı var ise, ProfileController a giderek kişiye ait profil id sini getirecek.             *
             */
            long authid = user.get().getId();
            String profileid =   profileManager.findByAuthId(FindByAuthIdDto.builder().authid(authid).build()).getBody();
            Optional<String> token = jwtTokenManager.createToken(profileid);
            /**
             * Eğer dönen değer, "" ise farklı dolu ise farklı işlem yapılacak.
             */
            if (profileid.equals("")){
                return DoLoginResponseDto.builder().error(500).build();
            }else{
                if(token.isPresent())
                    return DoLoginResponseDto.builder().profileid(profileid).token(token.get()).error(200).build();
                else
                    return DoLoginResponseDto.builder().error(411).build();
            }
        }
        return DoLoginResponseDto.builder().error(410).build();
    }

    public boolean verifyToken(String token){
        return jwtTokenManager.validateToken(token);
    }
}
