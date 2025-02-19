package com.marketing.dashboard.services;

import com.marketing.dashboard.dtos.CampaignChannelDTO;
import com.marketing.dashboard.entities.Campaign;
import com.marketing.dashboard.entities.CampaignChannel;
import com.marketing.dashboard.repositories.CampaignChannelRepository;
import com.marketing.dashboard.repositories.CampaignRepository;
import com.marketing.dashboard.repositories.ChannelRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CampaignChannelServiceTest {

    @Mock
    private CampaignChannelRepository campaignChannelRepository;

    @Mock
    private CampaignRepository campaignRepository;

    @Mock
    private ChannelRepository channelRepository;

    @InjectMocks
    private CampaignChannelService campaignChannelService;

    @Test
    void testGetCampaignChannelById() {
        CampaignChannel campaignChannel = new CampaignChannel();
        campaignChannel.setCampaignChannelId(1L);

        when(campaignChannelRepository.findById(1L)).thenReturn(Optional.of(campaignChannel));

        CampaignChannel result = campaignChannelService.getCampaignChannelById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getCampaignChannelId());
        verify(campaignChannelRepository).findById(1L);
    }

    @Test
    void testCreateCampaignChannel() {
        CampaignChannel campaignChannel = new CampaignChannel();

        when(campaignChannelRepository.save(any(CampaignChannel.class))).thenReturn(campaignChannel);

        CampaignChannel result = campaignChannelService.createCampaignChannel(campaignChannel);

        assertNotNull(result);
        verify(campaignChannelRepository).save(campaignChannel);
    }

    @Test
    void testDeleteCampaignChannel() {
        doNothing().when(campaignChannelRepository).deleteById(1L);

        campaignChannelService.deleteCampaignChannel(1L);

        verify(campaignChannelRepository).deleteById(1L);
    }

    @Test
    void testCreateCampaignWithChannels() {
        CampaignChannelDTO dto = new CampaignChannelDTO();
        dto.setCampaignName("New Campaign");

        Campaign campaign = new Campaign();
        campaign.setCampaignId(1L);
        campaign.setCampaignName("New Campaign");

        when(campaignRepository.save(any(Campaign.class))).thenReturn(campaign);
        when(channelRepository.findAllById(isNull())).thenReturn(List.of());
        CampaignChannelDTO result = campaignChannelService.createCampaignWithChannels(dto);

        assertNotNull(result);
        assertEquals("New Campaign", result.getCampaignName());
        verify(campaignRepository).save(any(Campaign.class));
    }
}