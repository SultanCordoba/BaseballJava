package deportes.beisbol.jpa.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QLigaHistoricoInt is a Querydsl query type for LigaHistoricoInt
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QLigaHistoricoInt extends EntityPathBase<LigaHistoricoInt> {

    private static final long serialVersionUID = -244731448L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLigaHistoricoInt ligaHistoricoInt = new QLigaHistoricoInt("ligaHistoricoInt");

    public final DateTimePath<java.sql.Timestamp> fechaActualizacion = createDateTime("fechaActualizacion", java.sql.Timestamp.class);

    public final NumberPath<Short> id = createNumber("id", Short.class);

    public final QIdioma idioma;

    public final QLigaHistorico ligaHistorico;

    public final StringPath nombre = createString("nombre");

    public final StringPath siglas = createString("siglas");

    public QLigaHistoricoInt(String variable) {
        this(LigaHistoricoInt.class, forVariable(variable), INITS);
    }

    public QLigaHistoricoInt(Path<? extends LigaHistoricoInt> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QLigaHistoricoInt(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QLigaHistoricoInt(PathMetadata<?> metadata, PathInits inits) {
        this(LigaHistoricoInt.class, metadata, inits);
    }

    public QLigaHistoricoInt(Class<? extends LigaHistoricoInt> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.idioma = inits.isInitialized("idioma") ? new QIdioma(forProperty("idioma")) : null;
        this.ligaHistorico = inits.isInitialized("ligaHistorico") ? new QLigaHistorico(forProperty("ligaHistorico"), inits.get("ligaHistorico")) : null;
    }

}

