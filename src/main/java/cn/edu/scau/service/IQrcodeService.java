package cn.edu.scau.service;

import org.springframework.web.multipart.MultipartFile;

public interface IQrcodeService {
    public MultipartFile getTreeQrcode(int id);
}
