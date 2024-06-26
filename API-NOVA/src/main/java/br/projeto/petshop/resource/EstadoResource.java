package br.projeto.petshop.resource;

import br.projeto.petshop.dto.EstadoDTO;
import br.projeto.petshop.service.EstadoService;
import br.projeto.petshop.validation.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;

import java.util.List;

@Path("/estados")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class EstadoResource {

    private static final Logger LOG = Logger.getLogger(EstadoResource.class);

    private final EstadoService estadoService;

    @Inject
    public EstadoResource(EstadoService estadoService) {
        this.estadoService = estadoService;
    }

    @GET
    @Path("/all")
    @Transactional
    public Response getAllEstados() {
        LOG.info("Buscando todos os estados");
        List<EstadoDTO> estados = estadoService.getAll();
        if (estados.isEmpty()) {
            LOG.info("Nenhum estado encontrado");
            return Response.status(Response.Status.NOT_FOUND).entity("Nenhum estado encontrado").build();
        } else {
            LOG.info("Retornando todos os estados");
            return Response.ok(estados).build();
        }
    }

    @GET
    @Path("/{id}")
    @Transactional
    public Response getEstadoById(@PathParam("id") long id) {
        LOG.info("Buscando estado pelo ID: " + id);
        try {
            EstadoDTO estado = estadoService.getById(id);
            LOG.info("Estado encontrado: " + estado);
            return Response.ok(estado).build();
        } catch (NotFoundException e) {
            LOG.error("Estado não encontrado", e);
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @POST
    @Path("/insert")
    @Transactional
    public Response insertEstado(@Valid EstadoDTO estadoDTO) {
        LOG.info("Inserindo novo estado: " + estadoDTO);
        try {
            estadoService.insert(estadoDTO);
            LOG.info("Estado inserido com sucesso");
            return Response.status(Response.Status.CREATED).build();
        } catch (ValidationException e) {
            LOG.error("Erro ao inserir o estado", e);
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/delete/{id}")
    @Transactional
    public Response deleteEstado(@PathParam("id") long id) {
        LOG.info("Deletando estado pelo ID: " + id);
        try {
            estadoService.delete(id);
            LOG.info("Estado deletado com sucesso");
            return Response.noContent().build();
        } catch (NotFoundException e) {
            LOG.error("Estado não encontrado", e);
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
}
