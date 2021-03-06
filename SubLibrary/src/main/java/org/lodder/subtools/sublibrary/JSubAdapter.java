package org.lodder.subtools.sublibrary;

import java.util.List;

import org.lodder.subtools.sublibrary.model.EpisodeFile;
import org.lodder.subtools.sublibrary.model.MovieFile;
import org.lodder.subtools.sublibrary.model.Subtitle;

public interface JSubAdapter {

  public abstract List<Subtitle> searchSubtitles(EpisodeFile episodeFile, String... sublanguageids);

  public abstract List<Subtitle> searchSubtitles(MovieFile movieFile, String... sublanguageids);
}
