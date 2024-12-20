package com.nusrat.PortfolioTrackerApplication.repositories;

import com.nusrat.PortfolioTrackerApplication.entities.User;
import org.hibernate.sql.ast.tree.expression.JdbcParameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


}
