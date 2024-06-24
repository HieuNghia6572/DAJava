package com.example.DAJava.services;


import com.example.DAJava.entity.ChiTietDonHang;
import com.example.DAJava.entity.DonHang;
import com.example.DAJava.entity.GioHang;
import com.example.DAJava.repository.ChiTietDonHangRepository;
import com.example.DAJava.repository.DonHangRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class DonHangService {
    @Autowired
    private DonHangRepository donhangRepository;
    @Autowired
    private ChiTietDonHangRepository chitietdonhangRepository;
    @Autowired
    private GioHangService giohangService; // Assuming you have a CartService
    @Transactional
    public DonHang createDonhang(String customerName, String customerAddress, String customerPhone, String customerNote , List<GioHang> giohangs) {
        DonHang order = new DonHang();
        order.setCustomerName(customerName);
        order.setCustomerAddress(customerAddress);
        order.setCustomerPhone(customerPhone);
        order.setCustomerNote(customerNote);
        order = donhangRepository.save(order);
        for (GioHang item : giohangs) {
            ChiTietDonHang chitiet = new ChiTietDonHang();
            chitiet.setDonhang(order);
            chitiet.setSanpham(item.getSanpham());
            chitiet.setSoluong(item.getSoluong());
            chitietdonhangRepository.save(chitiet);
        }
        // Optionally clear the cart after order placement
        giohangService.clearCart();
        return order;
    }
}
