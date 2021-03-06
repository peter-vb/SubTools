package org.lodder.subtools.sublibrary.data.tvrage.model;

/*
 *      Copyright (c) 2004-2013 Stuart Boston
 *
 *      This file is part of the TVRage API.
 *
 *      TVRage API is free software: you can redistribute it and/or modify
 *      it under the terms of the GNU General Public License as published by
 *      the Free Software Foundation, either version 3 of the License, or
 *      any later version.
 *
 *      TVRage API is distributed in the hope that it will be useful,
 *      but WITHOUT ANY WARRANTY; without even the implied warranty of
 *      MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *      GNU General Public License for more details.
 *
 *      You should have received a copy of the GNU General Public License
 *      along with TVRage API.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.commons.lang3.math.NumberUtils;

public class TVRageEpisodeNumber implements Comparable<TVRageEpisodeNumber>, Serializable {

    /*
     * Serial Version
     */
    private static final long serialVersionUID = 1L;
    /*
     * Properties
     */
    private static final int FACTOR = 1000;
    // The absolute episode number across all seasons
    private int absolute;
    // The episode number within the season
    private int episode;
    // The show season
    private int season;

    public TVRageEpisodeNumber() {
        this.season = 0;
        this.episode = 0;
        this.absolute = 0;
    }

    public TVRageEpisodeNumber(int season, int episode) {
        this.season = season;
        this.episode = episode;
        // Calculate the absolute if we are not passed one
        this.absolute = calculateAbsolute(season, episode);
    }

    public TVRageEpisodeNumber(int season, int episode, int absolute) {
        this.season = season;
        this.episode = episode;
        this.absolute = absolute;
    }

    public TVRageEpisodeNumber(String season, String episode) {
        this.season = NumberUtils.toInt(season, 0);
        this.episode = NumberUtils.toInt(episode, 0);
        // Calculate the absolute if we are not passed one
        this.absolute = calculateAbsolute(this.season, this.episode);
    }

    public TVRageEpisodeNumber(String season, String episode, String absolute) {
        this.season = NumberUtils.toInt(season, 0);
        this.episode = NumberUtils.toInt(episode, 0);
        this.absolute = NumberUtils.toInt(absolute, 0);
    }

    private int calculateAbsolute(int season, int episode) {
        // Make the season very large for comparison purposes (will handle up to 1,000 episodes)
        return ((season * FACTOR) + episode);
    }

    @Override
    public int compareTo(TVRageEpisodeNumber anotherEpisodeNumber) {
        int otherSeason = ((TVRageEpisodeNumber) anotherEpisodeNumber).getSeason();
        int otherEpisode = ((TVRageEpisodeNumber) anotherEpisodeNumber).getEpisode();

        return calculateAbsolute(season, episode) - calculateAbsolute(otherSeason, otherEpisode);
    }

    public int getAbsolute() {
        return absolute;
    }

    public int getEpisode() {
        return episode;
    }

    public int getSeason() {
        return season;
    }

    public String getSxE() {
        return String.format("%dx%d", season, episode);
    }

    public String getSxxEyy() {
        return String.format("S%2dE%2d", season, episode);
    }

    public void setAbsolute(int absolute) {
        this.absolute = absolute;
    }

    public void setAbsolute(String absolute) {
        this.absolute = NumberUtils.toInt(absolute, 0);
    }

    public void setEpisode(int episode) {
        this.episode = episode;
    }

    public void setEpisode(String episode) {
        this.episode = NumberUtils.toInt(episode, 0);
    }

    public void setSeason(int season) {
        this.season = season;
    }

    public void setSeason(String season) {
        this.season = NumberUtils.toInt(season, 0);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public boolean isValid() {
        // False if either is 0
        return (season * episode > 0);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + absolute;
        result = prime * result + episode;
        result = prime * result + season;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        TVRageEpisodeNumber other = (TVRageEpisodeNumber) obj;

        if (absolute == other.absolute) {
            // If the absolute matches, then assume the season and episode will
            return true;
        }

        if (season != other.season) {
            return false;
        }

        if (episode != other.episode) {
            return false;
        }

        return true;
    }
}
