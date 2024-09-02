package com.hc.localCulture.service;

import com.hc.localCulture.entity.Vendor;
import com.hc.localCulture.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VendorService {

    @Autowired
    private VendorRepository vendorRepository;

    public void saveVendor(Vendor vendor) {
        vendorRepository.save(vendor);
    }

    public void deleteVendor(Vendor vendor) {
        vendorRepository.delete(vendor);
    }
}
