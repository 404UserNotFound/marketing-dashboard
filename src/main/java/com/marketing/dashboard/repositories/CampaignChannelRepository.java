package com.marketing.dashboard.repositories;

import com.marketing.dashboard.entities.CampaignChannel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CampaignChannelRepository extends JpaRepository<CampaignChannel, Long> {
}
