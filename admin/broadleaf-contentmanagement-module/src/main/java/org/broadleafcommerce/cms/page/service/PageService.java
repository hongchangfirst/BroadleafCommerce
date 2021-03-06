/*
 * #%L
 * BroadleafCommerce CMS Module
 * %%
 * Copyright (C) 2009 - 2013 Broadleaf Commerce
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package org.broadleafcommerce.cms.page.service;

import net.sf.ehcache.Cache;

import org.broadleafcommerce.cms.page.domain.Page;
import org.broadleafcommerce.cms.page.domain.PageField;
import org.broadleafcommerce.cms.page.domain.PageTemplate;
import org.broadleafcommerce.common.locale.domain.Locale;
import org.broadleafcommerce.common.page.dto.PageDTO;

import java.util.List;
import java.util.Map;

/**
 * Created by bpolster.
 */
public interface PageService {


    /**
     * Returns the page with the passed in id.
     *
     * @param pageId - The id of the page.
     * @return The associated page.
     */
    public Page findPageById(Long pageId);

    /**
     * Returns the page-fields associated with a page.
     * @param pageId
     * @return
     */
    public Map<String, PageField> findPageFieldMapByPageId(Long pageId);

    /**
     * Returns the page template with the passed in id.
     *
     * @param id - the id of the page template
     * @return The associated page template.
     */
    public PageTemplate findPageTemplateById(Long id);
    
    /**
     * Saves the given {@link PageTemplate}
     * 
     * @param template the {@link PageTemplate} to save
     * @return the database-saved {@link PageTemplate}
     */
    public PageTemplate savePageTemplate(PageTemplate template);

    /**
     * Looks up the page from the backend datastore.   Processes the page's fields to
     * fix the URL if the site has overridden the URL for images.   If secure is true
     * and images are being overridden, the system will use https.
     *
     * @param currentSandbox - current sandbox
     * @param locale - current locale
     * @param uri - the URI to return a page for
     * @param ruleDTOs - ruleDTOs that are used as the data to process page rules
     * @param secure - set to true if current request is over HTTPS
     * @return
     */
    public PageDTO findPageByURI(Locale locale, String uri, Map<String,Object> ruleDTOs, boolean secure);
    
    /**
     * Returns all pages, regardless of any sandbox they are apart of
     * @return all {@link Page}s configured in the system
     */
    public List<Page> readAllPages();
    
    /**
     * Returns all page templates, regardless of any sandbox they are apart of
     * @return all {@link PageTemplate}s configured in the system
     */
    public List<PageTemplate> readAllPageTemplates();

    /**
     * Call to evict all known PageDTOs that are associated with the given page from cache
     * 
     * @param key
     */
    public void removePageFromCache(String key);

    Cache getPageCache();

    Cache getPageMapCache();

    /**
     * Builds a list of {@link PageDTO} objects from the given list of {@link Page} objects.
     * 
     * @param pageList
     * @param secure
     * @return
     */
    public List<PageDTO> buildPageDTOList(List<Page> pageList, boolean secure);

    String getPageMapCacheKey(String uri, Long sandBox, Long site);


}
