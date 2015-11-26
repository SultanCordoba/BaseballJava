package deportes.beisbol.jpa.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QParque is a Querydsl query type for Parque
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QParque extends EntityPathBase<Parque> {

    private static final long serialVersionUID = -1373844430L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QParque parque = new QParque("parque");

    public final QCiudad ciudad;

    public final SetPath<Equipo, QEquipo> equipos = this.<Equipo, QEquipo>createSet("equipos", Equipo.class, QEquipo.class, PathInits.DIRECT2);

    public final DateTimePath<java.sql.Timestamp> fechaActualizacion = createDateTime("fechaActualizacion", java.sql.Timestamp.class);

    public final SetPath<FranquiciaHistorico, QFranquiciaHistorico> franquiciaHistoricos = this.<FranquiciaHistorico, QFranquiciaHistorico>createSet("franquiciaHistoricos", FranquiciaHistorico.class, QFranquiciaHistorico.class, PathInits.DIRECT2);

    public final SetPath<Franquicia, QFranquicia> franquicias = this.<Franquicia, QFranquicia>createSet("franquicias", Franquicia.class, QFranquicia.class, PathInits.DIRECT2);

    public final NumberPath<Short> id = createNumber("id", Short.class);

    public final StringPath nombre = createString("nombre");

    public final SetPath<Partido, QPartido> partidos = this.<Partido, QPartido>createSet("partidos", Partido.class, QPartido.class, PathInits.DIRECT2);

    public final NumberPath<Byte> versionParque = createNumber("versionParque", Byte.class);

    public QParque(String variable) {
        this(Parque.class, forVariable(variable), INITS);
    }

    public QParque(Path<? extends Parque> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QParque(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QParque(PathMetadata<?> metadata, PathInits inits) {
        this(Parque.class, metadata, inits);
    }

    public QParque(Class<? extends Parque> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.ciudad = inits.isInitialized("ciudad") ? new QCiudad(forProperty("ciudad"), inits.get("ciudad")) : null;
    }

}

