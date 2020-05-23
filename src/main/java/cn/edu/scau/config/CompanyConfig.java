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
    public String verifCode;

    public void setVerifCode(String verifCode) {
        this.verifCode = verifCode;
    }
    public String getVerifCode(){
        return verifCode;
    }

    @Bean
    public Company company(){
        Company company = companyDao.selectCompany();
        return company;
    }
}
