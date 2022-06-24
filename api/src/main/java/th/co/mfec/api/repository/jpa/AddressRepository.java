package th.co.mfec.api.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import th.co.mfec.api.entity.jpa.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
    
}
