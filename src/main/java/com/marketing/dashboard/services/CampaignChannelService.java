package com.marketing.dashboard.services;

import com.marketing.dashboard.dtos.CampaignChannelDTO;
import com.marketing.dashboard.entities.Campaign;
import com.marketing.dashboard.entities.CampaignChannel;
import com.marketing.dashboard.entities.Channel;
import com.marketing.dashboard.repositories.CampaignChannelRepository;
import com.marketing.dashboard.repositories.CampaignRepository;
import com.marketing.dashboard.repositories.ChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CampaignChannelService {

    @Autowired
    private CampaignChannelRepository campaignChannelRepository;

    @Autowired
    private CampaignRepository campaignRepository;

    @Autowired
    private ChannelRepository channelRepository;


    public List<CampaignChannel> getAllCampaignChannels() {
        return campaignChannelRepository.findAll();
    }

    public CampaignChannel getCampaignChannelById(Long id) {
        return campaignChannelRepository.findById(id).orElse(null);
    }

    public CampaignChannel createCampaignChannel(CampaignChannel campaignChannel) {
        return campaignChannelRepository.save(campaignChannel);
    }

    public void deleteCampaignChannel(Long id) {
        campaignChannelRepository.deleteById(id);
    }

    public List<CampaignChannelDTO> getAllCampaignChannelMappings() {
        List<CampaignChannel> campaignChannels = campaignChannelRepository.findAll();

        Map<Long, CampaignChannelDTO> campaignMap = new HashMap<>();

        for (CampaignChannel campaignChannel : campaignChannels) {
            Long campaignId = campaignChannel.getCampaign().getId();
            String campaignName = campaignChannel.getCampaign().getCampaignName();
            Long channelId = campaignChannel.getChannel().getChannelId();
            String channelName = campaignChannel.getChannel().getName();
            String campaignDescription = campaignChannel.getCampaign().getCampaignDescription();
            Double campaignBudget = campaignChannel.getCampaign().getCampaignBudget();
            String campaignStatus = campaignChannel.getCampaign().getCampaignStatus();
            String campaignStartDate = campaignChannel.getCampaign().getCampaignStartDate();
            String campaignEndDate = campaignChannel.getCampaign().getCampaignEndDate();

            if (!campaignMap.containsKey(campaignId)) {
                campaignMap.put(campaignId, new CampaignChannelDTO(
                        campaignChannel.getCampaignChannelId(),
                        campaignId,
                        new ArrayList<>(),
                        new ArrayList<>(),
                        campaignName,
                        campaignDescription,
                        campaignBudget,
                        campaignStatus,
                        campaignStartDate,
                        campaignEndDate
                ));
            }

            CampaignChannelDTO dto = campaignMap.get(campaignId);
            dto.getChannelId().add(channelId);
            dto.getChannelNames().add(channelName);
        }

        return new ArrayList<>(campaignMap.values());
    }

    public CampaignChannelDTO createCampaignWithChannels(CampaignChannelDTO campaignChannelDTO) {
        Campaign newCampaign = new Campaign();
        newCampaign.setCampaignName(campaignChannelDTO.getCampaignName());
        newCampaign.setCampaignDescription(campaignChannelDTO.getCampaignDescription());
        newCampaign.setCampaignBudget(campaignChannelDTO.getCampaignBudget());
        newCampaign.setCampaignStatus(campaignChannelDTO.getCampaignStatus());
        newCampaign.setCampaignStartDate(campaignChannelDTO.getCampaignStartDate());
        newCampaign.setCampaignEndDate(campaignChannelDTO.getCampaignEndDate());
        campaignRepository.save(newCampaign);

        List<Channel> channels = channelRepository.findAllById(campaignChannelDTO.getChannelId());

        List<Long> channelIds = new ArrayList<>();
        List<String> channelNames = new ArrayList<>();

        for (Channel channel : channels) {
            CampaignChannel campaignChannel = new CampaignChannel();
            campaignChannel.setCampaign(newCampaign);
            campaignChannel.setChannel(channel);
            campaignChannelRepository.save(campaignChannel);

            channelIds.add(channel.getChannelId());
            channelNames.add(channel.getName());
        }

        return new CampaignChannelDTO(
                null,
                newCampaign.getId(),
                channelIds,
                channelNames,
                newCampaign.getCampaignName(),
                newCampaign.getCampaignDescription(),
                newCampaign.getCampaignBudget(),
                newCampaign.getCampaignStatus(),
                newCampaign.getCampaignStartDate(),
                newCampaign.getCampaignEndDate()
        );
    }

    public CampaignChannelDTO updateCampaignWithChannels(Long campaignId, CampaignChannelDTO campaignChannelDTO) {
        Campaign existingCampaign = campaignRepository.findById(campaignId)
                .orElseThrow(() -> new RuntimeException("Campaign not found"));

        if (campaignChannelDTO.getCampaignName() != null) {
            existingCampaign.setCampaignName(campaignChannelDTO.getCampaignName());
            existingCampaign.setCampaignDescription(campaignChannelDTO.getCampaignDescription());
            existingCampaign.setCampaignBudget(campaignChannelDTO.getCampaignBudget());
            existingCampaign.setCampaignStatus(campaignChannelDTO.getCampaignStatus());
            existingCampaign.setCampaignStartDate(campaignChannelDTO.getCampaignStartDate());
            existingCampaign.setCampaignEndDate(campaignChannelDTO.getCampaignEndDate());
        }
        campaignRepository.save(existingCampaign);

        List<CampaignChannel> existingMappings = campaignChannelRepository.findByCampaignId(campaignId);
        campaignChannelRepository.deleteAll(existingMappings);

        List<Channel> newChannels = channelRepository.findAllById(campaignChannelDTO.getChannelId());

        List<Long> channelIds = new ArrayList<>();
        List<String> channelNames = new ArrayList<>();

        for (Channel channel : newChannels) {
            CampaignChannel newMapping = new CampaignChannel();
            newMapping.setCampaign(existingCampaign);
            newMapping.setChannel(channel);
            campaignChannelRepository.save(newMapping);

            channelIds.add(channel.getChannelId());
            channelNames.add(channel.getName());
        }

        return new CampaignChannelDTO(
                null,
                existingCampaign.getId(),
                channelIds,
                channelNames,
                existingCampaign.getCampaignName(),
                existingCampaign.getCampaignDescription(),
                existingCampaign.getCampaignBudget(),
                existingCampaign.getCampaignStatus(),
                existingCampaign.getCampaignStartDate(),
                existingCampaign.getCampaignEndDate()
        );
    }
}
