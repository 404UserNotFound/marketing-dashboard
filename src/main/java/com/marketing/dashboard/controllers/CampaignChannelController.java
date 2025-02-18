package com.marketing.dashboard.controllers;

import com.marketing.dashboard.dtos.CampaignChannelDTO;
import com.marketing.dashboard.entities.CampaignChannel;
import com.marketing.dashboard.repositories.CampaignChannelRepository;
import com.marketing.dashboard.services.CampaignChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/campaign-channels")
public class CampaignChannelController {

    @Autowired
    private CampaignChannelService campaignChannelService;

    @Autowired
    private CampaignChannelRepository campaignChannelRepository;

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

    @DeleteMapping("/{id}")
    public void deleteCampaignChannelById(@PathVariable Long id) {
        campaignChannelService.deleteCampaignChannel(id);
    }

    @GetMapping("/mappings")
    public ResponseEntity<List<CampaignChannelDTO>> getCampaignChannelMappings() {
        List<CampaignChannelDTO> campaignChannelMappings = campaignChannelService.getAllCampaignChannelMappings();
        return ResponseEntity.ok(campaignChannelMappings);
    }

    @PostMapping("/mappings")
    public ResponseEntity<CampaignChannelDTO> createCampaignWithChannels(@RequestBody CampaignChannelDTO dto) {
        CampaignChannelDTO createdCampaign = campaignChannelService.createCampaignWithChannels(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCampaign);
    }

    @PutMapping("/mappings/{campaignId}")
    public ResponseEntity<CampaignChannelDTO> updateCampaignWithChannels(
            @PathVariable Long campaignId,
            @RequestBody CampaignChannelDTO dto) {
        CampaignChannelDTO updatedCampaign = campaignChannelService.updateCampaignWithChannels(campaignId, dto);
        return ResponseEntity.ok(updatedCampaign);
    }

    @DeleteMapping("/mappings/{campaignChannelId}")
    public ResponseEntity<String> deleteCampaignChannel(@PathVariable Long campaignChannelId) {
        campaignChannelService.deleteCampaignChannel(campaignChannelId);
        return ResponseEntity.ok("Deleted mapping.");
    }
    @GetMapping("/mappings/{channelId}")
    public ResponseEntity<List<CampaignChannel>> findCampaignChannelByChannelId(@PathVariable Long channelId) {
        List<CampaignChannel> campaignChannelMappings = campaignChannelRepository.findCampaignChannelByChannelChannelId(channelId);
        return ResponseEntity.ok(campaignChannelMappings);
    }
}
