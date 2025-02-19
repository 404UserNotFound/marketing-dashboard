package com.marketing.dashboard.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marketing.dashboard.entities.Campaign;
import com.marketing.dashboard.services.CampaignService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CampaignController.class)
@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class CampaignControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CampaignService campaignService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllCampaigns() throws Exception {
        List<Campaign> campaigns = Arrays.asList(new Campaign(
                "Black Friday", "Huge Discounts", "Active", 5000.0, "2024-06-01", "2024-08-31"),
                new Campaign("New Year Sale", "Holiday discounts", "Cancelled", 7000.0, "2024-12-01", "2025-01-15"
                ));
        when(campaignService.getAllCampaigns()).thenReturn(campaigns);

        mockMvc.perform(get("/campaigns"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2));
    }

    @Test
    void testGetCampaignById() throws Exception {
        Campaign campaign = new Campaign(
                "Black Friday", "Huge discounts", "Active", 10000.0, "2024-11-25", "2024-11-29"
        );
        when(campaignService.getCampaignById(1L)).thenReturn(campaign);

        mockMvc.perform(get("/campaigns/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.campaignName").value("Black Friday"));
    }

    @Test
    void testCreateCampaign() throws Exception {
        Campaign campaign = new Campaign(
                "Cyber Monday", "Online discounts", "Completed", 8000.0, "2024-12-02", "2024-12-02"
        );
        when(campaignService.createCampaign(any(Campaign.class))).thenReturn(campaign);

        mockMvc.perform(post("/campaigns")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(campaign)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.campaignName").value("Cyber Monday"));
    }

    @Test
    void testUpdateCampaign() throws Exception {
        Campaign updatedCampaign = new Campaign(
                "Updated Campaign", "Updated Description", "Completed", 12000.0, "2024-01-01", "2024-01-31"
        );
        when(campaignService.updateCampaign(any(Campaign.class), eq(1L))).thenReturn(updatedCampaign);

        mockMvc.perform(put("/campaigns/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedCampaign)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.campaignName").value("Updated Campaign"));
    }

    @Test
    void testDeleteCampaign() throws Exception {
        doNothing().when(campaignService).deleteCampaign(1L);

        mockMvc.perform(delete("/campaigns/1"))
                .andExpect(status().isOk());

        verify(campaignService, times(1)).deleteCampaign(1L);
    }
}