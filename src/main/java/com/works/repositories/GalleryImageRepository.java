package com.works.repositories;

import com.works.entities.GalleriesImages;
import com.works.entities.GalleryImage;
import com.works.entities.ProductsImages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GalleryImageRepository extends JpaRepository<GalleryImage, Integer> {

    @Query(value = "SELECT g.GALLERYNAME, gm.GID, gm.GALLERY_IMAGE,gm.G_ID from GALLERY_IMAGE as gm INNER JOIN GALLERY as g on g.ID = gm.G_ID where g.ID= ?1", nativeQuery = true)
    List<GalleriesImages> galleriesImagesList(int id);
}
