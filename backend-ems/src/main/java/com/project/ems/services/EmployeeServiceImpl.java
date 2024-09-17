package com.project.ems.services;

import com.project.ems.dto.ImageUtil;
import com.project.ems.entity.EmpDetails;
import com.project.ems.repositories.EmployeeRepository;
import com.project.ems.security.WebSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * implementation of the EmployeeService interface
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private WebSecurityConfig webSecurityConfig;
    @Autowired
    private EmployeeServiceImpl(EmployeeRepository theEmployeeRepository){
        this.employeeRepository = theEmployeeRepository;
    }
    @Override
    public List<EmpDetails> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public EmpDetails findById(int id) {
        Optional<EmpDetails> res = employeeRepository.findById(id);
        EmpDetails empDetails = null;
        if(res.isPresent()){
            empDetails = res.get();
        }
        else {
            // we didn't find the employee
            throw new RuntimeException("Did not find id - " + id);
        }
        return empDetails;
    }

    @Override
    public EmpDetails findByEmail(String email) {
        return employeeRepository.findByEmail(email).get();
    }

    @Override
    public EmpDetails save(EmpDetails empDetails) {
        String role = "ROLE_" +  empDetails.getRole();
        empDetails.setRole(role);
        if(empDetails.getPassword()!=null){
            empDetails.setPassword(webSecurityConfig.passwordEncoder().encode(empDetails.getPassword()));
        }
        else{
            empDetails.setPassword(employeeRepository.findByEmail(empDetails.getEmail()).get().getPassword());
        }
        return employeeRepository.save(empDetails);
    }

    @Override
    public void deleteById(int id) {
        employeeRepository.deleteById((id));
    }

    @Override
    public EmpDetails updateImage(MultipartFile file, String email) throws IOException {
        Optional<EmpDetails> empDetails = employeeRepository.findByEmail(email);
        empDetails.get().setImage(ImageUtil.compressImage(file.getBytes()));
        return employeeRepository.save(empDetails.get());
    }

    @Override
    public byte[] viewImage(String email) {
        Optional<EmpDetails> empDetails = employeeRepository.findByEmail(email);
        if(empDetails.get().getImage()!=null){
            return ImageUtil.decompressImage(empDetails.get().getImage());
        }
        return null;
    }

    @Override
    public boolean checkPassword(String password, String email) {
        return webSecurityConfig.passwordEncoder().matches(password, employeeRepository.findByEmail(email).get().getPassword());
    }
}

