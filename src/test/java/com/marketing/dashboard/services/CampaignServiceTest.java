package com.marketing.dashboard.services;

import com.marketing.dashboard.entities.Campaign;
import com.marketing.dashboard.repositories.CampaignRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CampaignServiceTest {

    @Mock
    private CampaignRepository campaignRepository;

    @InjectMocks
    private CampaignService campaignService;

    @Test
    void testGetCampaignById() {
        Campaign campaign = new Campaign();
        campaign.setCampaignId(1L);
        campaign.setCampaignName("Test Campaign");

        when(campaignRepository.findById(1L)).thenReturn(Optional.of(campaign));

        Campaign result = campaignService.getCampaignById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(campaignRepository).findById(1L);
    }

    @Test
    void testCreateCampaign() {
        Campaign campaign = new Campaign();
        campaign.setCampaignName("New Campaign");

        when(campaignRepository.save(any(Campaign.class))).thenReturn(campaign);

        Campaign result = campaignService.createCampaign(campaign);

        assertNotNull(result);
        assertEquals("New Campaign", result.getCampaignName());
        verify(campaignRepository).save(campaign);
    }

    @Test
    void testDeleteCampaign() {
        doNothing().when(campaignRepository).deleteById(1L);

        campaignService.deleteCampaign(1L);

        verify(campaignRepository).deleteById(1L);
    }
}
