package spring.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.shop.model.Vendor;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long> {
}
