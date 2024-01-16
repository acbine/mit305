package com.example.tae.repository.RegistrationRepository;

import com.example.tae.entity.Contract.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractRepository extends JpaRepository<Contract, Integer> {
}
