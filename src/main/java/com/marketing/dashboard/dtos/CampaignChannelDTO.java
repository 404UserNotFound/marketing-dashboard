package com.marketing.dashboard.dtos;

import java.util.List;

public class CampaignChannelDTO {
    private Long campaignChannelId;
    private Long campaignId;
    private List<Long> channelId;
    private List<String> channelNames;
    private String campaignName;
    private String campaignDescription;
    private Double campaignBudget;
    private String campaignStatus;
    private String campaignStartDate;
    private String campaignEndDate;

    public CampaignChannelDTO() {}

    public CampaignChannelDTO(
            Long campaignChannelId,
            Long campaignId,
            List<Long> channelId,
            List<String> channelNames,
            String campaignName,
            String campaignDescription,
            Double campaignBudget,
            String campaignStatus,
            String campaignStartDate,
            String campaignEndDate
    ) {
        this.campaignChannelId = campaignChannelId;
        this.campaignId = campaignId;
        this.channelId = channelId;
        this.channelNames = channelNames;
        this.campaignName = campaignName;
        this.campaignDescription = campaignDescription;
        this.campaignBudget = campaignBudget;
        this.campaignStatus = campaignStatus;
        this.campaignStartDate = campaignStartDate;
        this.campaignEndDate = campaignEndDate;
    }

    public Long getCampaignChannelId() {
        return campaignChannelId;
    }

    public void setCampaignChannelId(Long campaignChannelId) {
        this.campaignChannelId = campaignChannelId;
    }

    public Long getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(Long campaignId) {
        this.campaignId = campaignId;
    }

    public List<Long> getChannelId() {
        return channelId;
    }

    public void setChannelId(List<Long> channelId) {
        this.channelId = channelId;
    }

    public List<String> getChannelNames() {
        return channelNames;
    }

    public void setChannelNames(List<String> channelNames) {
        this.channelNames = channelNames;
    }

    public String getCampaignName() {
        return campaignName;
    }

    public void setCampaignName(String campaignName) {
        this.campaignName = campaignName;
    }

    public Double getCampaignBudget() {
        return campaignBudget;
    }

    public void setCampaignBudget(Double campaignBudget) {
        this.campaignBudget = campaignBudget;
    }

    public String getCampaignStatus() {
        return campaignStatus;
    }

    public void setCampaignStatus(String campaignStatus) {
        this.campaignStatus = campaignStatus;
    }

    public String getCampaignStartDate() {
        return campaignStartDate;
    }

    public void setCampaignStartDate(String campaignStartDate) {
        this.campaignStartDate = campaignStartDate;
    }

    public String getCampaignEndDate() {
        return campaignEndDate;
    }

    public void setCampaignEndDate(String campaignEndDate) {
        this.campaignEndDate = campaignEndDate;
    }

    public String getCampaignDescription() {
        return campaignDescription;
    }

    public void setCampaignDescription(String campaignDescription) {
        this.campaignDescription = campaignDescription;
    }
}
