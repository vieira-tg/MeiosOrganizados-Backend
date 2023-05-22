package com.meiosorganizado.config;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequestInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.handler.WebRequestHandlerInterceptorAdapter;

import java.util.logging.Handler;

@Component
public class RestMethodInterceptor extends HandlerInterceptorAdapter //extends WebRequestHandlerInterceptorAdapter {
{
/*    static final ObjectMapper mapper = Jsons.getDefaultObjectMapperIgnoreFailAll();

    @Autowired
    RecebedorService recebedorService;

    @Value("${srp-pix.paths-restrict-by-regex}")

    String pathsRestrictByRegex;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)

            throws IOException {

        val startTimeMillis = System.currentTimeMillis();

        mdc:

        {

            Mdc.mdc();

        }

        setRecebedor:

        {

            val brbUser = request.getHeader(BRB_USER_HTTP_HEADER);

            if (request.getServletPath().matches(pathsRestrictByRegex)) {

                if (Strings.isNullOrEmpty(brbUser)) {

                    throw new AccessDeniedException("Requisição sem brb-user!");

                }

                val recebedor = recebedorService.findByBrbUser(brbUser);

                if (recebedor == null) {

                    throw new SrpRecebedorNotFoundException("Recebedor não existente para o brb-user!");

                }

                RecebedorInRequest.setRecebedor(recebedor);

                request.setAttribute("recebedor", recebedor);

            }

        }

        val httpInfo = HttpInfo
                .builder()

                .correlationId(Mdc.get())

                .startTimeMillis(startTimeMillis)

                .path(request.getRequestURL().toString())

                .query(request.getQueryString())

                .sessionId(request.getSession().getId())

                .requestHeaders(extractHeaderNamesToMap(request))

                .build();

        request.setAttribute("httpInfo", httpInfo);

        LOGGER.info("\n{}", mapper.writerWithView(HttpInfo.RequestView.class).writeValueAsString(httpInfo));

        return true;

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)

            throws IOException {

        val endTimeMillis = System.currentTimeMillis();

        response.setHeader("X-Correlation-ID", Mdc.get().toString());

        val httpInfo = (HttpInfo) request.getAttribute("httpInfo");

        httpInfo.setEndTimeMillis(endTimeMillis);

        httpInfo.setResponseHeaders(extractHeaderNamesToMap(response));

        if (ex != null) LOGGER.error("\n{}", ex);

        LOGGER.info("\n{}", mapper.writerWithView(HttpInfo.ResponseView.class).writeValueAsString(httpInfo));

        Mdc.remove();

    }*/

}
