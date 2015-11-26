package deportes.beisbol.jpa.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QVuelta is a Querydsl query type for Vuelta
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QVuelta extends EntityPathBase<Vuelta> {

    private static final long serialVersionUID = -1183991227L;

    public static final QVuelta vuelta = new QVuelta("vuelta");

    public final DateTimePath<java.sql.Timestamp> fechaActualizacion = createDateTime("fechaActualizacion", java.sql.Timestamp.class);

    public final NumberPath<Byte> id = createNumber("id", Byte.class);

    public final StringPath nombre = createString("nombre");

    public final SetPath<Partido, QPartido> partidos = this.<Partido, QPartido>createSet("partidos", Partido.class, QPartido.class, PathInits.DIRECT2);

    public final SetPath<Record, QRecord> records = this.<Record, QRecord>createSet("records", Record.class, QRecord.class, PathInits.DIRECT2);

    public QVuelta(String variable) {
        super(Vuelta.class, forVariable(variable));
    }

    public QVuelta(Path<? extends Vuelta> path) {
        super(path.getType(), path.getMetadata());
    }

    public QVuelta(PathMetadata<?> metadata) {
        super(Vuelta.class, metadata);
    }

}

