package com.marketing.dashboard.controllers;

import com.marketing.dashboard.entities.CampaignChannel;
import com.marketing.dashboard.services.CampaignChannelService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CampaignChannelControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CampaignChannelService campaignChannelService;

    @Test
    public void testGetAllCampaignChannels() throws Exception {
        when(campaignChannelService.getAllCampaignChannels()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/campaign-channels"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));

        verify(campaignChannelService, times(1)).getAllCampaignChannels();
    }

    @Test
    public void testGetCampaignChannelById() throws Exception {
        CampaignChannel campaignChannel = new CampaignChannel();
        campaignChannel.setCampaignChannelId(1L);

        when(campaignChannelService.getCampaignChannelById(1L)).thenReturn(campaignChannel);

        mockMvc.perform(get("/campaign-channels/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.campaignChannelId").value(1));

        verify(campaignChannelService, times(1)).getCampaignChannelById(1L);
    }

    @Test
    public void testDeleteCampaignChannel() throws Exception {
        doNothing().when(campaignChannelService).deleteCampaignChannel(1L);

        mockMvc.perform(delete("/campaign-channels/1"))
                .andExpect(status().isOk());

        verify(campaignChannelService, times(1)).deleteCampaignChannel(1L);
    }

    @Test
    public void testGetCampaignChannelMappings() throws Exception {
        when(campaignChannelService.getAllCampaignChannelMappings()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/campaign-channels/mappings"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));

        verify(campaignChannelService, times(1)).getAllCampaignChannelMappings();
    }


}
