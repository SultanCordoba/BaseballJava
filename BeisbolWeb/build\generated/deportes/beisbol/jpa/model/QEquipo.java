package deportes.beisbol.jpa.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QEquipo is a Querydsl query type for Equipo
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QEquipo extends EntityPathBase<Equipo> {

    private static final long serialVersionUID = -1673907215L;

    private static final PathInits INITS = new PathInits("*", "franquiciaHistorico.franquicia.liga.siglasEs", "participante.temporada.id");

    public static final QEquipo equipo = new QEquipo("equipo");

    public final StringPath abreviatura = createString("abreviatura");

    public final StringPath archivoEscudo = createString("archivoEscudo");

    public final NumberPath<Byte> campeon = createNumber("campeon", Byte.class);

    public final DateTimePath<java.sql.Timestamp> fechaActualizacion = createDateTime("fechaActualizacion", java.sql.Timestamp.class);

    public final DatePath<java.util.Date> fechaFin = createDate("fechaFin", java.util.Date.class);

    public final DatePath<java.util.Date> fechaInicio = createDate("fechaInicio", java.util.Date.class);

    public final QFranquiciaHistorico franquiciaHistorico;

    public final NumberPath<Short> id = createNumber("id", Short.class);

    public final StringPath nombreCompletoEs = createString("nombreCompletoEs");

    public final StringPath nombreTablasEs = createString("nombreTablasEs");

    public final QParque parque;

    public final QParticipante participante;

    public final SetPath<PartidoEquipo, QPartidoEquipo> partidoEquipos = this.<PartidoEquipo, QPartidoEquipo>createSet("partidoEquipos", PartidoEquipo.class, QPartidoEquipo.class, PathInits.DIRECT2);

    public final SetPath<Roster, QRoster> rosters = this.<Roster, QRoster>createSet("rosters", Roster.class, QRoster.class, PathInits.DIRECT2);

    public QEquipo(String variable) {
        this(Equipo.class, forVariable(variable), INITS);
    }

    public QEquipo(Path<? extends Equipo> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QEquipo(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QEquipo(PathMetadata<?> metadata, PathInits inits) {
        this(Equipo.class, metadata, inits);
    }

    public QEquipo(Class<? extends Equipo> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.franquiciaHistorico = inits.isInitialized("franquiciaHistorico") ? new QFranquiciaHistorico(forProperty("franquiciaHistorico"), inits.get("franquiciaHistorico")) : null;
        this.parque = inits.isInitialized("parque") ? new QParque(forProperty("parque"), inits.get("parque")) : null;
        this.participante = inits.isInitialized("participante") ? new QParticipante(forProperty("participante"), inits.get("participante")) : null;
    }

}

