package com.marketing.dashboard.controllers;

import com.marketing.dashboard.entities.Campaign;
import com.marketing.dashboard.services.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/campaigns")
public class CampaignController {

    @Autowired
    private CampaignService campaignService;

    @GetMapping("")
    public List<Campaign> getAllCampaigns() {
        return campaignService.getAllCampaigns();
    }

    @GetMapping("/{id}")
    public Campaign getCampaignById(@PathVariable("id") Long id) {
        return campaignService.getCampaignById(id);
    }

    @PostMapping("")
    public Campaign createCampaign(@RequestBody Campaign campaign) {
        return campaignService.createCampaign(campaign);
    }

    @PutMapping("/{id}")
    public Campaign updateCampaign(@PathVariable("id") Long id, @RequestBody Campaign campaign) {
        return campaignService.updateCampaign(campaign, id);
    }

    @DeleteMapping("/{id}")
    public void deleteCampaign(@PathVariable("id") Long id) {
        campaignService.deleteCampaign(id);
    }
}
