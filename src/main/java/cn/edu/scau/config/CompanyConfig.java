package cn.edu.scau.config;

import cn.edu.scau.dao.CompanyDao;
import cn.edu.scau.model.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CompanyConfig {
    @Autowired
    private CompanyDao companyDao;

    @Bean
    public Company company(){
        Company company = companyDao.selectCompany();
        return company;
    }
}
