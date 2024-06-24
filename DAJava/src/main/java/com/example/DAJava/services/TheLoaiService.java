package com.example.DAJava.services;

import com.example.DAJava.entity.TheLoai;
import com.example.DAJava.repository.ITheLoaiRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
//Dương Đức Tài
public class TheLoaiService {
    private final ITheLoaiRepository theLoaiRepository;
    /**
     * Retrieve all categories from the database.
     * @return a list of categories
     */
    public List<TheLoai> getAllTheloai() {
        return theLoaiRepository.findAll();
    }
    /**
     * Retrieve a category by its id.
     * @param id the id of the category to retrieve
     * @return an Optional containing the found category or empty if not found
     */
    public Optional<TheLoai> getTheloaiById(Long id) {
        return theLoaiRepository.findById(id);
    }
    /**
     * Add a new category to the database.
     * @param theLoai the category to add
     */
    public void addTheloai(TheLoai theLoai) {
        theLoaiRepository.save(theLoai);
    }
    /**
     * Update an existing category.
     * @param theLoai the category with updated information
     */
    public void updateTheloai(@NotNull TheLoai theLoai) {
        TheLoai existingTheloai = theLoaiRepository.findById(theLoai.getId())
                .orElseThrow(() -> new IllegalStateException("Category with ID " +
                        theLoai.getId() + " does not exist."));
        existingTheloai.setTen(theLoai.getTen());
        theLoaiRepository.save(existingTheloai);
    }
    /**
     * Delete a category by its id.
     * @param id the id of the category to delete
     */
    public void deleteTheloaiById(Long id) {
        if (!theLoaiRepository.existsById(id)) {
            throw new IllegalStateException("Category with ID " + id + " does not exist.");
        }
        theLoaiRepository.deleteById(id);
    }
}
