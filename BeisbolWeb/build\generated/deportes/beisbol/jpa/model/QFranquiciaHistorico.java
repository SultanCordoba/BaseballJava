package deportes.beisbol.jpa.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QFranquiciaHistorico is a Querydsl query type for FranquiciaHistorico
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QFranquiciaHistorico extends EntityPathBase<FranquiciaHistorico> {

    private static final long serialVersionUID = 37137711L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFranquiciaHistorico franquiciaHistorico = new QFranquiciaHistorico("franquiciaHistorico");

    public final StringPath abreviatura = createString("abreviatura");

    public final StringPath archivoEscudo = createString("archivoEscudo");

    public final SetPath<Equipo, QEquipo> equipos = this.<Equipo, QEquipo>createSet("equipos", Equipo.class, QEquipo.class, PathInits.DIRECT2);

    public final DateTimePath<java.sql.Timestamp> fechaActualizacion = createDateTime("fechaActualizacion", java.sql.Timestamp.class);

    public final DateTimePath<java.util.Date> fechaFin = createDateTime("fechaFin", java.util.Date.class);

    public final DateTimePath<java.util.Date> fechaInicio = createDateTime("fechaInicio", java.util.Date.class);

    public final QFranquicia franquicia;

    public final SetPath<FranquiciaHistoricoInt, QFranquiciaHistoricoInt> franquiciaHistoricoInts = this.<FranquiciaHistoricoInt, QFranquiciaHistoricoInt>createSet("franquiciaHistoricoInts", FranquiciaHistoricoInt.class, QFranquiciaHistoricoInt.class, PathInits.DIRECT2);

    public final NumberPath<Short> id = createNumber("id", Short.class);

    public final StringPath nombreCompletoEs = createString("nombreCompletoEs");

    public final StringPath nombreTablasEs = createString("nombreTablasEs");

    public final QParque parque;

    public final NumberPath<Byte> versionFranquicia = createNumber("versionFranquicia", Byte.class);

    public QFranquiciaHistorico(String variable) {
        this(FranquiciaHistorico.class, forVariable(variable), INITS);
    }

    public QFranquiciaHistorico(Path<? extends FranquiciaHistorico> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QFranquiciaHistorico(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QFranquiciaHistorico(PathMetadata<?> metadata, PathInits inits) {
        this(FranquiciaHistorico.class, metadata, inits);
    }

    public QFranquiciaHistorico(Class<? extends FranquiciaHistorico> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.franquicia = inits.isInitialized("franquicia") ? new QFranquicia(forProperty("franquicia"), inits.get("franquicia")) : null;
        this.parque = inits.isInitialized("parque") ? new QParque(forProperty("parque"), inits.get("parque")) : null;
    }

}

