package com.marketing.dashboard.controllers;

import com.marketing.dashboard.entities.CampaignChannel;
import com.marketing.dashboard.services.CampaignChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/campaign-channels")
public class CampaignChannelController {

    @Autowired
    private CampaignChannelService campaignChannelService;

    @GetMapping("")
    public List<CampaignChannel> getAllCampaignChannels() {
        return campaignChannelService.getAllCampaignChannels();
    }

    @GetMapping("/{id}")
    public CampaignChannel getCampaignChannelById(@PathVariable Long id) {
        return campaignChannelService.getCampaignChannelById(id);
    }

    @PostMapping("")
    public CampaignChannel createCampaignChannel(@RequestBody CampaignChannel campaignChannel) {
        return campaignChannelService.createCampaignChannel(campaignChannel);
    }

    //todo:no update here

    @DeleteMapping("/{id}")
    public void deleteCampaignChannelById(@PathVariable Long id) {
        campaignChannelService.deleteCampaignChannel(id);
    }
}
