package com.Huy.Moneycare.repository;

import com.Huy.Moneycare.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    // 1. Truy vấn theo tên phương thức (Spring tự sinh SQL)
    List<Transaction> findByAccountId(Long accountId);

    // 2. Truy vấn JPQL bằng @Query
    @Query("SELECT t FROM Transaction t WHERE t.accountId = :accountId "
            + "AND t.transactionDate BETWEEN :fromDate AND :toDate")
    List<Transaction> searchByAccountAndDate(@Param("accountId") Long accountId,
            @Param("fromDate") String fromDate,
            @Param("toDate") String toDate);

    // 3. Truy vấn SQL thuần (native query)
    @Query(value = "SELECT * FROM transactions WHERE amount >= :minAmount ORDER BY amount DESC", nativeQuery = true)
    List<Transaction> findLargeTransactions(@Param("minAmount") Double minAmount);
}