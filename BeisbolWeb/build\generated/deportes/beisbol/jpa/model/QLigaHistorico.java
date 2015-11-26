package deportes.beisbol.jpa.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QLigaHistorico is a Querydsl query type for LigaHistorico
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QLigaHistorico extends EntityPathBase<LigaHistorico> {

    private static final long serialVersionUID = 744773799L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLigaHistorico ligaHistorico = new QLigaHistorico("ligaHistorico");

    public final DateTimePath<java.sql.Timestamp> fechaActualizacion = createDateTime("fechaActualizacion", java.sql.Timestamp.class);

    public final DateTimePath<java.util.Date> fechaFin = createDateTime("fechaFin", java.util.Date.class);

    public final DateTimePath<java.util.Date> fechaInicio = createDateTime("fechaInicio", java.util.Date.class);

    public final NumberPath<Short> id = createNumber("id", Short.class);

    public final QLiga liga;

    public final SetPath<LigaHistoricoInt, QLigaHistoricoInt> ligaHistoricoInts = this.<LigaHistoricoInt, QLigaHistoricoInt>createSet("ligaHistoricoInts", LigaHistoricoInt.class, QLigaHistoricoInt.class, PathInits.DIRECT2);

    public final StringPath nombre = createString("nombre");

    public final StringPath siglas = createString("siglas");

    public final SetPath<Temporada, QTemporada> temporadas = this.<Temporada, QTemporada>createSet("temporadas", Temporada.class, QTemporada.class, PathInits.DIRECT2);

    public final NumberPath<Integer> versionLiga = createNumber("versionLiga", Integer.class);

    public QLigaHistorico(String variable) {
        this(LigaHistorico.class, forVariable(variable), INITS);
    }

    public QLigaHistorico(Path<? extends LigaHistorico> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QLigaHistorico(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QLigaHistorico(PathMetadata<?> metadata, PathInits inits) {
        this(LigaHistorico.class, metadata, inits);
    }

    public QLigaHistorico(Class<? extends LigaHistorico> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.liga = inits.isInitialized("liga") ? new QLiga(forProperty("liga")) : null;
    }

}

