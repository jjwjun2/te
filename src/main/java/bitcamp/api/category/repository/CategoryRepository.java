package bitcamp.api.category.repository;


import bitcamp.api.category.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

interface ICategoryRepository { }

public interface CategoryRepository extends JpaRepository<Category, Long>, ICategoryRepository { }