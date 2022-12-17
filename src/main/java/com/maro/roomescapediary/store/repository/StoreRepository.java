package com.maro.roomescapediary.store.repository;

import com.maro.roomescapediary.store.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Integer>, StoreCustomRepository {

}
