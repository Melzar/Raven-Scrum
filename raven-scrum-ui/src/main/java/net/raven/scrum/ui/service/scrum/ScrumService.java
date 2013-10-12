package net.raven.scrum.ui.service.scrum;

import net.raven.scrum.core.rest.dto.scrum.ScrumboardDTO;

public interface ScrumService
{
	public ScrumboardDTO prepareDataForScrumboard(Long idProject);
}
